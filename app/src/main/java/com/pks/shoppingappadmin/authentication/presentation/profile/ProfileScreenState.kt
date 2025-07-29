package com.pks.shoppingappadmin.authentication.presentation.profile

import com.pks.shoppingappadmin.category.domain.model.UserData

data class ProfileScreenState(
    var isLoading:Boolean = false,
    var error:String="",
    var data: UserData = UserData()
)