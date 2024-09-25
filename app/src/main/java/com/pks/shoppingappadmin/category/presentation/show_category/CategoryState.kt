package com.pks.shoppingappadmin.category.presentation.show_category

import com.pks.shoppingappadmin.domain.model.CategoryModel

data class CategoryState(
    var isLoading:Boolean = false,
    var data:List<CategoryModel>? = null,
    var error:String = ""
)