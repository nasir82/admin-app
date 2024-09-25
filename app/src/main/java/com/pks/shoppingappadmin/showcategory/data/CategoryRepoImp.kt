package com.pks.shoppingappadmin.showcategory.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingappadmin.category.presentation.show_category.CategoryState
import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.domain.model.CategoryModel
import com.pks.shoppingappadmin.domain.model.ProductModel
import com.pks.shoppingappadmin.showcategory.domain.repo.CategoryRepo
import com.pks.shoppingappadmin.showcategory.presentation.ProductState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class CategoryRepoImp @Inject constructor(val db:FirebaseFirestore):CategoryRepo {


    override suspend fun getCategories(): Flow<ResultState<CategoryState>> = callbackFlow {

        Log.d("inside category collector", "collectng category")
        trySend(ResultState.Loading)

          db.collection("Category").get().addOnSuccessListener {
              val instances = it.map{obj->
                  obj.toObject(CategoryModel::class.java)
              }.toList()

              trySend(
                  ResultState.Success(CategoryState(data = instances))
              )
          }
              .addOnFailureListener {

                  trySend(ResultState.Error(message = it.message?: "Firebase error"))
              }


        awaitClose {
            close()
        }
    }

    override suspend fun getProducts(): Flow<ResultState<ProductState>> = callbackFlow {
        trySend(ResultState.Loading)

        db.collection("Products").get().addOnSuccessListener {
            val instances = it.map{obj->
                obj.toObject(ProductModel::class.java)
            }.toList()

            trySend(
                ResultState.Success(ProductState(data = instances))
            )
        }
            .addOnFailureListener {

                trySend(ResultState.Error(message = it.message?: "Firebase error"))
            }


        awaitClose {
            close()
        }
    }
}