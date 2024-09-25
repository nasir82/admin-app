package com.pks.shoppingappadmin.authentication.domain.usecase

import com.pks.shoppingappadmin.authentication.domain.repo.AuthenticationRepo
import com.pks.shoppingappadmin.authentication.presentation.UpdateUserDataState
import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.domain.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserDataUsecase @Inject constructor(private val repo: AuthenticationRepo) {

    fun updateUserData(userData: UserData): Flow<ResultState<UpdateUserDataState>> {
        return repo.updateUserData(userData)
    }
}