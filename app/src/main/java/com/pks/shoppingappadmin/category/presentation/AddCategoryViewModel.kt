package com.pks.shoppingappadmin.category.presentation

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.storage.FirebaseStorage
import com.pks.shoppingappadmin.category.domain.use_case.AddCategoryUseCase
import com.pks.shoppingappadmin.category.domain.use_case.GetCategoryUseCases
import com.pks.shoppingappadmin.category.presentation.show_category.CategoryState
import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.domain.model.CategoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


@HiltViewModel
class AddCategoryViewModel @Inject constructor(private val addCategoryUseCase: AddCategoryUseCase,val getCategoryUseCases: GetCategoryUseCases) :
    ViewModel() {

    var category = mutableStateOf(CategoryModel())
    private val addCategoryState = MutableStateFlow(AddCategoryState())
    val _addCategoryState = addCategoryState
    private val _categoryState2 = MutableStateFlow(CategoryState())
    val categoryState2 = _categoryState2.asStateFlow()

    init {
        getCategories()
    }
    fun addCategory(
        image: String?,
        name: String = "",
        parentId: String = "",
        isFeature: Boolean = false,
        networkState: Boolean
    ) {

        if (image == null) Log.d("calling upload", "thi time $image")
        if (image.isNullOrEmpty()) {
            addCategoryState.value = AddCategoryState(error = "No Image Selected")
        } else if (!networkState) {
            addCategoryState.value = AddCategoryState(error = "No internet connection")
        } else {
            addCategoryState.value = AddCategoryState(isLoading = true)
            Log.d("calling upload", "Main method is calling....")
            viewModelScope.launch {
                val res = uploadImage(image)
                if (res != null)
                    addCategoryUseCase.addCategory(CategoryModel(res, name, parentId, isFeature))
                        .collectLatest { state ->
                            when (state) {
                                is ResultState.Error -> {
                                    addCategoryState.value =
                                        AddCategoryState(error = state.message.toString())
                                }

                                ResultState.Loading -> {
                                    addCategoryState.value = AddCategoryState(isLoading = true)
                                }

                                is ResultState.Success -> {
                                    var lst: MutableList<CategoryModel> = _categoryState2.value.data!!.toMutableList()
                                    lst.add(CategoryModel(res,name,parentId,isFeature))
                                    _categoryState2.value = CategoryState(data = lst.toList())
                                    addCategoryState.value = AddCategoryState(success = state.data)
                                }
                            }
                        }

            }

        }
    }

    private fun getCategories() {
        _categoryState2.value = CategoryState(isLoading = true)
        Log.d("inside category collector", "collectng category viewmodel")
        viewModelScope.launch {
            delay(10000)
            getCategoryUseCases.getCategories().collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        Log.d("inside category collector", "collectng category error")
                        _categoryState2.value = CategoryState(error = it.message)
                    }

                    ResultState.Loading -> {
                        Log.d("inside category collector", "collectng category loading")
                        _categoryState2.value = CategoryState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        Log.d(
                            "inside category collector",
                            "collectng category success ${it.data.data}"
                        )
                        _categoryState2.value = CategoryState(data = it.data.data)
                        Log.d(
                            "inside category collector",
                            "collectng category origin ${categoryState2.value.data}"
                        )
                    }
                }
            }
        }
    }

    private suspend fun uploadImage(image: String): String? {
        Log.d("calling upload", "Main method is calling....")
        val db = FirebaseStorage.getInstance().reference.child("CategoryImage")
            .child("${System.currentTimeMillis()}")
        return try {
            db.putFile(Uri.parse(image)).await()
            db.downloadUrl.await().toString()
        } catch (
            e: FirebaseException
        ) {
            addCategoryState.value = AddCategoryState(error = e.message ?: "Can not upload image")
            return null
        }


    }
}

data class AddCategoryState(
    var isLoading: Boolean = false,
    val success: String = "",
    val error: String = ""
)