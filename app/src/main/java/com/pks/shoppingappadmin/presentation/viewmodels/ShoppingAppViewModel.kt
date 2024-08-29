package com.pks.shoppingappadmin.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.domain.model.CategoryModel
import com.pks.shoppingappadmin.domain.model.UserData
import com.pks.shoppingappadmin.domain.repo.ShoppingAppRepp
import com.pks.shoppingappadmin.domain.use_case.CreateUserUseCase
import com.pks.shoppingappadmin.domain.use_case.GetUserUseCase
import com.pks.shoppingappadmin.presentation.navigation.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingAppViewModel @Inject constructor(
    private val shoppingAppRepp: ShoppingAppRepp,
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    var category = mutableStateOf(CategoryModel())
    private val categoryState = mutableStateOf(CategoryState())
    val _categoryState = categoryState

    /// this is customizeable
    private val _signupScreenState = MutableStateFlow(SignupScreenState())
    val signupScreenState = _signupScreenState.asStateFlow()
    private val _loginScreenState = MutableStateFlow(SignupScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()
    private val _profileScreenState = MutableStateFlow(ProfileScreenState())
    val profileScreenState = _profileScreenState.asStateFlow()
    //okay

    fun createUser(userData: UserData) {
        viewModelScope.launch {

            createUserUseCase.createUser(userData).collect {
                when (it) {
                    is ResultState.Error -> {
                        _signupScreenState.value = SignupScreenState(error = it.message)
                    }

                    ResultState.Loading -> {
                        _signupScreenState.value = SignupScreenState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _signupScreenState.value = SignupScreenState(userData = it.data)
                    }
                }
            }
        }

    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {

            createUserUseCase.singIn(email = email, password = password).collect {
                when (it) {
                    is ResultState.Error -> {
                        _loginScreenState.value = SignupScreenState(error = it.message)
                    }

                    ResultState.Loading -> {
                        _loginScreenState.value = SignupScreenState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _loginScreenState.value = SignupScreenState(userData = it.data)
                    }
                }
            }
        }

    }

    fun getUserByUid(uid: String) {
        viewModelScope.launch {
            getUserUseCase.getUserWithuid(uid).collectLatest {

                when (it) {
                    is ResultState.Error -> {
                        _profileScreenState.value =
                            ProfileScreenState(errorMessage = it.message.toString())
                    }

                    ResultState.Loading -> {
                        _profileScreenState.value = ProfileScreenState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _profileScreenState.value = ProfileScreenState(userData = it.data)
                    }
                }
            }
        }
    }

    fun addCategory() {
        viewModelScope.launch {
            Log.d("upload", "Entry taken place ${category.value}")
            shoppingAppRepp.addCategory(category.value).collectLatest { state ->
                Log.d("upload", "Entry taken place inside")
                when (state) {
                    is ResultState.Error -> {
                        categoryState.value = CategoryState(error = state.message.toString())
                        Log.d("upload", state.message)
                    }

                    ResultState.Loading -> {
                        categoryState.value = CategoryState(isLoading = true)
                        Log.d("upload", "Loading")
                    }

                    is ResultState.Success -> {
                        categoryState.value = CategoryState(success = state.data)
                        Log.d("upload", state.data)
                    }
                }
            }

        }

    }
}

data class CategoryState(
    val success: String = "",
    val isLoading: Boolean = false,
    val error: String = ""
)

data class ProfileScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: UserData? = null
)

data class SignupScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val userData: String? = null
)