package com.pks.shoppingappadmin.addproduct.domain.repo

import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow

interface AddProductRepo {
    suspend fun addProduct(product: ProductModel): Flow<ResultState<String>>
    suspend fun addImages(images: List<String>): List<String>
}