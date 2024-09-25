package com.pks.shoppingappadmin.showcategory.domain.di

import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingappadmin.showcategory.data.CategoryRepoImp
import com.pks.shoppingappadmin.showcategory.domain.repo.CategoryRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ShowCategoryDomain {

    @Provides
    @Singleton
    fun provideShowCategoryRepo(db:FirebaseFirestore):CategoryRepo{
        return CategoryRepoImp(db)
    }
}