package com.pks.shoppingappadmin.products.presentation

import com.pks.shoppingappadmin.products.domain.model.ProductModel

data class ProductState(
    var isLoading:Boolean = false,
    var data:List<ProductModel>? = null,
    var error:String = ""
)