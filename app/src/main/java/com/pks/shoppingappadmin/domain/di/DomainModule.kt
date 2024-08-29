package com.pks.shoppingappadmin.domain.di

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
object DomainModule {
  @Singleton
  @Provides
  fun providesRepo(db:FirebaseFirestore,auth: FirebaseAuth):ShoppingAppRepp{
      return ShoppingAppRepoImp(db,auth);
  }

}