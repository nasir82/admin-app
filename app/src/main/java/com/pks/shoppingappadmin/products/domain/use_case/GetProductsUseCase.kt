package com.pks.shoppingappadmin.products.domain.use_case

import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.products.domain.repo.AddProductRepo
import com.pks.shoppingappadmin.products.presentation.ProductState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private  val addProductRepo: AddProductRepo) {
    suspend fun getProducts():  Flow<ResultState<ProductState>> {
        return addProductRepo.getProducts()
    }
}