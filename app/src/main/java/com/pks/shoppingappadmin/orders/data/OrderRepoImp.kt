package com.pks.shoppingappadmin.orders.data

import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.orders.domain.model.OrderModel
import com.pks.shoppingappadmin.orders.domain.repo.OrderRepo
import com.pks.shoppingappadmin.orders.presentation.OrderScreenState
import com.pks.shoppingappadmin.orders.presentation.UpdateOrderState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class OrderRepoImp @Inject constructor(val db:FirebaseFirestore):OrderRepo{
    override suspend fun getAllOrder(): Flow<ResultState<OrderScreenState>> = callbackFlow{
        trySend(ResultState.Loading)

        db.collection("Orders").get().addOnSuccessListener {
            val instances = it.map{obj->
                obj.toObject(OrderModel::class.java)
            }.toList()

            trySend(
                ResultState.Success(OrderScreenState(data = instances))
            )
        }
            .addOnFailureListener {

                trySend(ResultState.Error(message = it.message?: "Firebase error"))
            }


        awaitClose {
            close()
        }
    }

    override suspend fun updateOrder(order: OrderModel): Flow<ResultState<UpdateOrderState>> = callbackFlow{
        trySend(ResultState.Loading)

        db.collection("Orders").document(order.id!!).set(order).addOnSuccessListener {
            trySend(
                ResultState.Success(UpdateOrderState(success = true))
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