package com.pks.shoppingappadmin.category.domain.di

import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingappadmin.category.data.AddCategoryRepoImp
import com.pks.shoppingappadmin.category.domain.repo.AddCategoryRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class CategoryModule {
    @Provides
    fun provideAddCategoryRepo(db: FirebaseFirestore):AddCategoryRepo{
        return  AddCategoryRepoImp(db)
    }
}