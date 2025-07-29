package com.pks.shoppingappadmin.category.domain.repo

import com.pks.shoppingappadmin.category.presentation.show_category.CategoryState
import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.category.domain.model.CategoryModel
import kotlinx.coroutines.flow.Flow

interface AddCategoryRepo {
    suspend fun addCategory(category: CategoryModel): Flow<ResultState<String>>
    suspend fun getCategories(): Flow<ResultState<CategoryState>>
}