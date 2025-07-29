package com.pks.shoppingappadmin.authentication.domain.repo

import com.pks.shoppingappadmin.authentication.presentation.UpdateUserDataState
import com.pks.shoppingappadmin.authentication.presentation.profile.ProfileScreenState
import com.pks.shoppingappadmin.category.domain.model.UserData
import com.pks.shoppingappadmin.common.ResultState
import kotlinx.coroutines.flow.Flow


interface AuthenticationRepo {
   fun registerUserWithEmailAndPassword(userData: UserData): Flow<ResultState<String>>
   fun signInWithEmailAndPassword(email:String,password:String): Flow<ResultState<String>>
   fun getUserDataByUid(id:String): Flow<ResultState<ProfileScreenState>>
   fun updateUserData(userData: UserData): Flow<ResultState<UpdateUserDataState>>

}