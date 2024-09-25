package com.pks.shoppingappadmin.addproduct.data

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.pks.shoppingappadmin.addproduct.domain.repo.AddProductRepo
import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.domain.model.ProductModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AddProductRepoImp @Inject constructor(val db:FirebaseFirestore,val storage: FirebaseStorage):AddProductRepo {

    override suspend fun addProduct(product: ProductModel): Flow<ResultState<String>> = callbackFlow {
        Log.d("upload","Entry taken place in function")
        trySend(ResultState.Loading)
        db.collection("Products").add(product).addOnSuccessListener {
            trySend(ResultState.Success(data = "Added to the category"))
        }
            .addOnFailureListener {
                trySend(ResultState.Error(message = it.message.toString()))
            }
        awaitClose{
            Log.d("uploaded","Closed")
            close()
        }

    }

    override suspend fun addImages(images: List<String>): List<String> {
        val urlList:ArrayList<String> = ArrayList<String>()
        try {
            images.forEach {
                val  ref = storage.reference.child("ProductImages")
                ref.putFile(Uri.parse(it)).await()
                val link = ref.downloadUrl.await()
                urlList.add(link.toString())
            }
            return urlList.toList()
        }catch (e:Exception){
            return  ArrayList<String>().toList()
        }
    }
}