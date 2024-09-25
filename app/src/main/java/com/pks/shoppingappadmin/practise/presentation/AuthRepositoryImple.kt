package com.pks.shoppingappadmin.practise.presentation

import com.pks.shoppingappadmin.practise.AuthRepositoryInterface
import com.pks.shoppingappadmin.practise.DataError
import com.pks.shoppingappadmin.practise.Result
import com.pks.shoppingappadmin.practise.Userclass
import java.io.IOException

class AuthRepositoryImple: AuthRepositoryInterface {
    override suspend fun register(password: String): Result<Userclass, DataError.NetworkError> {
        // api logic
       return try {
            val user = Userclass(
                fullName = "nasir",
                token = "toke",
                email = "abc@gmail.com"
            )
            Result.Success(user)
        }catch (e:IOException){
            when(e.message){
                "abc" -> Result.Error(DataError.NetworkError.REQUEST_TIMEOUT)
                else -> Result.Error(DataError.NetworkError.TOO_MANY_REQUEST)
            }
        }
    }
}