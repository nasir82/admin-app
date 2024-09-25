package com.pks.shoppingappadmin.showcategory.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.domain.model.ProductModel
import com.pks.shoppingappadmin.showcategory.domain.repo.CategoryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(val db: FirebaseFirestore, private val rep: CategoryRepo) :
    ViewModel() {

//    private val _categoryState = MutableStateFlow(CategoryState())
//    val categoryState = _categoryState.asStateFlow()

    private val _productState = MutableStateFlow(ProductState())
    val productState = _productState.asStateFlow()
//    val featuredCategory = categoryState.value.data?.let { data ->
//        data.filter { it.isFeatured }.asFlow()
//    }
    var statusList = mutableStateOf<List<ProductModel>>(listOf())

    init {
        //getCategory()
        getProduct()
    }

//    private fun getCategory() {
//        _categoryState.value = CategoryState(isLoading = true)
//        Log.d("inside category collector", "collectng category viewmodel")
//        viewModelScope.launch {
//            delay(10000)
//            rep.getCategories().collectLatest {
//                when (it) {
//                    is ResultState.Error -> {
//                        Log.d("inside category collector", "collectng category error")
//                        _categoryState.value = CategoryState(error = it.message)
//                    }
//
//                    ResultState.Loading -> {
//                        Log.d("inside category collector", "collectng category loading")
//                        _categoryState.value = CategoryState(isLoading = true)
//                    }
//
//                    is ResultState.Success -> {
//                        Log.d("inside category collector", "collectng category success ${it.data.data}")
//                        _categoryState.value = CategoryState(data = it.data.data)
//                        Log.d("inside category collector", "collectng category origin ${categoryState.value.data}")
//                    }
//                }
//            }
//        }
//    }
    fun getProduct() {
        viewModelScope.launch {
            Log.d("gettign products...","start")
            rep.getProducts().collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        _productState.value = ProductState(error = it.message)
                    }

                    ResultState.Loading -> {
                        _productState.value = ProductState(isLoading = true)
                    }

                    is ResultState.Success -> {
                         statusList.value  = it.data.data ?: emptyList()
                        _productState.value = ProductState(data = it.data.data)

                        Log.d("gettign products...","end with ${it.data.data}")
                    }
                }
            }
            Log.d("gettign products...","end")
        }
    }
}