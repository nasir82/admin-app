package com.pks.shoppingappadmin.domain.model

import java.time.LocalDateTime

data class CategoryModel(
    var name:String="",
    var date: String = LocalDateTime.now().toString(),
    var createdBy:String = "Nasir"
)

data class UserData(
    var name:String?="",
    var email:String?="",
    var password:String?="",
    var phone:String?=""
)
