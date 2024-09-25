package com.pks.shoppingappadmin.authentication.domain.usecase


import com.pks.shoppingappadmin.authentication.domain.repo.AuthenticationRepo
import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.domain.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val repo: AuthenticationRepo) {
    fun createUser(userData: UserData): Flow<ResultState<String>> {
       return repo.registerUserWithEmailAndPassword(userData)
    }

    fun singIn(email:String,password:String):Flow<ResultState<String>>{
        return repo.signInWithEmailAndPassword(email=email,password=password)
    }
}