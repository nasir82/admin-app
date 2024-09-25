package com.pks.shoppingappadmin.practise

class UserDataValidator {
    fun validatePassword(password:String): Result<Unit, PasswordError> {
            if(password.length<9) return Result.Error(PasswordError.TOO_SHORT)
            val hasUpperCaseChar = password.any{
                it.isUpperCase()
            }
            if(!hasUpperCaseChar) return Result.Error(PasswordError.NO_UPPERCASE)
          val hasDigit = password.any{
              it.isDigit()
          }
        if(!hasDigit) return Result.Error(PasswordError.NO_DIGIT)

        return Result.Success(Unit)
    }

    enum class PasswordError: Error {
        TOO_SHORT,
        NO_UPPERCASE,
        NO_SPECIAL_CHARACTER,
        NO_DIGIT
    }


}