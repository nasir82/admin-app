package com.pks.shoppingappadmin.category.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingappadmin.category.domain.repo.AddCategoryRepo
import com.pks.shoppingappadmin.category.presentation.show_category.CategoryState
import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.category.domain.model.CategoryModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AddCategoryRepoImp @Inject constructor(private val db: FirebaseFirestore): AddCategoryRepo {

    override suspend fun addCategory(category: CategoryModel): Flow<ResultState<String>> = callbackFlow {
        Log.d("upload","Entry taken place in function")
        trySend(ResultState.Loading)
        db.collection("Category").add(category).addOnSuccessListener {
            trySend(ResultState.Success(data = "Added new category"))

        }
            .addOnFailureListener {
                trySend(ResultState.Error( message = it.message.toString()))
            }
        awaitClose{
            Log.d("upload","Closed")
            close()
        }

    }

    override suspend fun getCategories(): Flow<ResultState<CategoryState>> =callbackFlow {

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
}