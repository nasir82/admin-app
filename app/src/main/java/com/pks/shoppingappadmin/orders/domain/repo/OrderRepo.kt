package com.pks.shoppingappadmin.orders.domain.repo

import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.orders.domain.model.OrderModel
import com.pks.shoppingappadmin.orders.presentation.OrderScreenState
import com.pks.shoppingappadmin.orders.presentation.UpdateOrderState
import kotlinx.coroutines.flow.Flow

interface OrderRepo {
    suspend fun getAllOrder(): Flow<ResultState<OrderScreenState>>
    suspend fun updateOrder(order:OrderModel): Flow<ResultState<UpdateOrderState>>

}