package com.pks.shoppingappadmin.authentication.domain.usecase


import com.pks.shoppingappadmin.authentication.domain.repo.AuthenticationRepo
import com.pks.shoppingappadmin.authentication.presentation.profile.ProfileScreenState
import com.pks.shoppingappadmin.common.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repo: AuthenticationRepo) {

    fun getUserWithuid(uid:String):Flow<ResultState<ProfileScreenState>>{
        return repo.getUserDataByUid(uid)
    }
}