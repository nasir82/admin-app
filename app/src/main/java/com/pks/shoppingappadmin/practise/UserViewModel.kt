package com.pks.shoppingappadmin.practise

import androidx.lifecycle.ViewModel

class UserViewModel(
    private val userDataValidator: UserDataValidator
): ViewModel() {

    fun onRegisterClick(password:String){
        when(val result = userDataValidator.validatePassword(password)){
            is Result.Error -> {
                when(result.error){
                    UserDataValidator.PasswordError.TOO_SHORT -> TODO()
                    UserDataValidator.PasswordError.NO_UPPERCASE -> TODO()
                    UserDataValidator.PasswordError.NO_SPECIAL_CHARACTER -> TODO()
                    UserDataValidator.PasswordError.NO_DIGIT -> TODO()
                }
            }
            is Result.Success -> {

            }
        }
    }
}