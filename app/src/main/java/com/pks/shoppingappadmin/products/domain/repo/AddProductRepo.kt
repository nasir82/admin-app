package com.pks.shoppingappadmin.products.domain.repo

import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.products.domain.model.ProductModel
import com.pks.shoppingappadmin.products.presentation.ProductState
import kotlinx.coroutines.flow.Flow

interface AddProductRepo {
    suspend fun addProduct(product: ProductModel): Flow<ResultState<String>>
    suspend fun addImages(images: List<String>): List<String>
    suspend fun getProducts(): Flow<ResultState<ProductState>>
}