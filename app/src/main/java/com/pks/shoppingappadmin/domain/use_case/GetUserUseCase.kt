package com.pks.shoppingappadmin.domain.use_case


import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.domain.model.UserData
import com.pks.shoppingappadmin.domain.repo.ShoppingAppRepp
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repo:ShoppingAppRepp) {

    fun getUserWithuid(uid:String):Flow<ResultState<UserData>>{
        return repo.getUserDataByUid(uid)
    }
}