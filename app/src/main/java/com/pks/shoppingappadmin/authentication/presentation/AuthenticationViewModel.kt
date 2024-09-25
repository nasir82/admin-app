package com.pks.shoppingappadmin.authentication.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pks.shoppingappadmin.authentication.domain.usecase.CreateUserUseCase
import com.pks.shoppingappadmin.authentication.domain.usecase.GetUserUseCase
import com.pks.shoppingappadmin.authentication.domain.usecase.UpdateUserDataUsecase
import com.pks.shoppingappadmin.authentication.presentation.profile.ProfileScreenState
import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.domain.model.UserData
import com.pks.shoppingappadmin.presentation.viewmodels.SignupScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserDataUsecase: UpdateUserDataUsecase
) : ViewModel() {
    private val _signupScreenState = MutableStateFlow(SignupScreenState())
    val signupScreenState = _signupScreenState.asStateFlow()
    private val _userDataUploadState = MutableStateFlow(UpdateUserDataState())
    val userDataUploadState = _userDataUploadState.asStateFlow()
    private val _loginScreenState = MutableStateFlow(SignupScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()
    private val _profileScreenState = MutableStateFlow(ProfileScreenState())
    val profileScreenState = _profileScreenState.asStateFlow()
    val profileUi = mutableStateOf<ProfileScreenState>(ProfileScreenState())

//    fun getUserData(uid:String){
//        val db = FirebaseFirestore.getInstance()
//        db.collection("Users").document(uid).get().addOnSuccessListener {
//            Log.d("user data collected", it.data.toString())
//        }
//    }

    fun alterState() {
        // _profileScreenState.value = ProfileScreenState(isLoading = true)
        Log.d("Printing the data", profileUi.value.data.toString())
        Log.d("Printing the data", profileScreenState.value.data.toString())
        Log.d("Printing the data", profileScreenState.value.toString())
    }

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
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = false,
                            data = userData
                        )
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
        Log.d("Data is loading befor", profileScreenState.value.toString())
        _profileScreenState.value = _profileScreenState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            Log.d("Data is loading", "data")
            delay(6000)
            getUserUseCase.getUserWithuid(uid).collectLatest {

                when (it) {
                    is ResultState.Error -> {
                        Log.d("Data is loading", "error")
                        _profileScreenState.value =
                            _profileScreenState.value.copy(
                                isLoading = false,
                                error = it.message.toString()
                            )
                    }

                    ResultState.Loading -> {
                        Log.d("Data is loading", "Loading")
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        Log.d("Data is loading", "in succsess")
                        Log.d("Data is loading", it.data.data.toString())
                        profileUi.value = ProfileScreenState(data = it.data.data)

                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = false,
                            data = it.data.data
                        )
                        Log.d("Data is loading in", profileScreenState.value.data.toString())
                    }
                }
                Log.d("Data is loading after", profileScreenState.value.toString())
            }

        }

    }

    fun updateUserdata(userData: UserData) {
        viewModelScope.launch {
            updateUserDataUsecase.updateUserData(userData).collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        _userDataUploadState.value =
                            UpdateUserDataState(error = it.message.toString())
                    }

                    ResultState.Loading -> {
                        _userDataUploadState.value = UpdateUserDataState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = false,
                            data = userData
                        )
                        _userDataUploadState.value = UpdateUserDataState(isLoaded = true)
                    }
                }
            }
        }
    }

    fun updateState(){
        _userDataUploadState.value = UpdateUserDataState()
    }

}

data class UpdateUserDataState(
    val isLoading: Boolean = false,
    val isLoaded: Boolean = false,
    val error: String = ""

)