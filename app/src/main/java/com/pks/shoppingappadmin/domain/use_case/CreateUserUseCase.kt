package com.pks.shoppingappadmin.domain.use_case


import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.domain.model.UserData
import com.pks.shoppingappadmin.domain.repo.ShoppingAppRepp
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val repo:ShoppingAppRepp) {
    fun createUser(userData: UserData): Flow<ResultState<String>> {
       return repo.registerUserWithEmailAndPassword(userData)
    }

    fun singIn(email:String,password:String):Flow<ResultState<String>>{
        return repo.signInWithEmailAndPassword(email=email,password=password)
    }
}