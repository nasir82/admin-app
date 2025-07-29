package com.pks.shoppingappadmin.category.domain.model

data class CategoryModel(
    var image:String = "",
    var name:String = "",
    var parentId:String = "",
    var isFeatured:Boolean = false,
)

data class UserData(
    var name:String?="",
    var email:String?="",
    var address:String?="",
    var password:String?="",
    var phone:String?=""
)
