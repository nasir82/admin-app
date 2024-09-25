package com.pks.shoppingappadmin.authentication.presentation.profile

import com.pks.shoppingappadmin.domain.model.UserData

data class ProfileScreenState(
    var isLoading:Boolean = false,
    var error:String="",
    var data: UserData = UserData()
)