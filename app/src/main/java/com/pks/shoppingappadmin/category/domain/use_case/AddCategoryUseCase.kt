package com.pks.shoppingappadmin.category.domain.use_case

import com.pks.shoppingappadmin.category.domain.repo.AddCategoryRepo
import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.category.domain.model.CategoryModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddCategoryUseCase @Inject constructor(private  val addCategoryRepo: AddCategoryRepo) {

    suspend fun addCategory(category: CategoryModel): Flow<ResultState<String>> {
        return addCategoryRepo.addCategory(category)
    }
}