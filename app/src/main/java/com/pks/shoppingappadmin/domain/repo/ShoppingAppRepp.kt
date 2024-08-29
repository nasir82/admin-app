package com.pks.shoppingappadmin.domain.repo

import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.domain.model.CategoryModel
import com.pks.shoppingappadmin.domain.model.UserData
import kotlinx.coroutines.flow.Flow


interface ShoppingAppRepp {
   fun registerUserWithEmailAndPassword(userData: UserData): Flow<ResultState<String>>
   fun signInWithEmailAndPassword(email:String,password:String): Flow<ResultState<String>>
   fun getUserDataByUid(id:String): Flow<ResultState<UserData>>
   suspend fun addCategory(category:CategoryModel):Flow<ResultState<String>>
}