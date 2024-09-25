package com.pks.shoppingappadmin.addproduct.domain.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.pks.shoppingappadmin.addproduct.data.AddProductRepoImp
import com.pks.shoppingappadmin.addproduct.domain.repo.AddProductRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AddProductModule {
  @Provides
  @Singleton
  fun provideAddProductRepo(db:FirebaseFirestore,storage: FirebaseStorage):AddProductRepo{
      return AddProductRepoImp( db =db, storage = storage)
  }
}