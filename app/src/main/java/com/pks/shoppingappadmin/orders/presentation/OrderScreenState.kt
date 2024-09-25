package com.pks.shoppingappadmin.orders.presentation

import com.pks.shoppingappadmin.orders.domain.model.OrderModel

data class OrderScreenState(
    val isLoading:Boolean = false,
    val error:String= "",
    var data:List<OrderModel> = emptyList()
)
data class UpdateOrderState(
    val isLoading:Boolean = false,
    val error:Boolean = false,
    var success:Boolean = false,
    val errorMessage:String= ""
)