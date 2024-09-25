package com.pks.shoppingappadmin.orders.domain.di

import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingappadmin.orders.data.OrderRepoImp
import com.pks.shoppingappadmin.orders.domain.repo.OrderRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderModule {
    @Singleton
    @Provides
    fun providesRepo(db: FirebaseFirestore): OrderRepo {
        return OrderRepoImp(db)
    }

}