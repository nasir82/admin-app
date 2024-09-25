package com.pks.shoppingappadmin.showcategory.presentation

import com.pks.shoppingappadmin.domain.model.ProductModel

data class ProductState(
    var isLoading:Boolean = false,
    var data:List<ProductModel>? = null,
    var error:String = ""
)