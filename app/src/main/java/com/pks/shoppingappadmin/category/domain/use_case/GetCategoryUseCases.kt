package com.pks.shoppingappadmin.category.domain.use_case

import com.pks.shoppingappadmin.category.domain.repo.AddCategoryRepo
import com.pks.shoppingappadmin.category.presentation.show_category.CategoryState
import com.pks.shoppingappadmin.common.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryUseCases @Inject constructor(private  val addCategoryRepo: AddCategoryRepo) {

    suspend fun getCategories(): Flow<ResultState<CategoryState>> {
        return addCategoryRepo.getCategories()
    }
}