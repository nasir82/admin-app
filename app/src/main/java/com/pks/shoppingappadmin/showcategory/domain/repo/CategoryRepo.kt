package com.pks.shoppingappadmin.showcategory.domain.repo

import com.pks.shoppingappadmin.category.presentation.show_category.CategoryState
import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.showcategory.presentation.ProductState
import kotlinx.coroutines.flow.Flow

interface CategoryRepo {
    suspend fun getCategories(): Flow<ResultState<CategoryState>>
    suspend fun getProducts(): Flow<ResultState<ProductState>>
}