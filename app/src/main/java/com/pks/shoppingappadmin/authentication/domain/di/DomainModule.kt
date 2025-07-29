package com.pks.shoppingappadmin.authentication.domain.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingappadmin.authentication.data.repo.ShoppingAppRepoImp
import com.pks.shoppingappadmin.authentication.domain.repo.AuthenticationRepo
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
  fun providesRepo(db:FirebaseFirestore,auth: FirebaseAuth): AuthenticationRepo {
      return ShoppingAppRepoImp(db,auth);
  }

    @Provides
    fun providesFirebaseAuth():FirebaseAuth{
        return  FirebaseAuth.getInstance()
    }

}