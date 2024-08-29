package com.pks.shoppingappadmin.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingappadmin.data.repo.ShoppingAppRepoImp
import com.pks.shoppingappadmin.domain.repo.ShoppingAppRepp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providesDataRepository():FirebaseFirestore{
        return  FirebaseFirestore.getInstance()
    }


    @Provides
    fun providesFirebaseAuth():FirebaseAuth{
        return  FirebaseAuth.getInstance()
    }
}