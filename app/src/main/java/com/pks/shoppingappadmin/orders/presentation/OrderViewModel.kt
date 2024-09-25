package com.pks.shoppingappadmin.orders.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.orders.domain.model.OrderModel
import com.pks.shoppingappadmin.orders.domain.repo.OrderRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrderViewModel @Inject constructor(val repo:OrderRepo):ViewModel() {

    private val _orderScreenState = MutableStateFlow(OrderScreenState())
    val orderScreenState = _orderScreenState.asStateFlow()
    private val _updateOrderScreenState = MutableStateFlow(UpdateOrderState())
    val updateOrderScreenState = _updateOrderScreenState.asStateFlow()

    init {
        getOrders()
    }
    fun getOrders(){

        viewModelScope.launch {

            Log.d("THis is order viewmodel","collection order")
            repo.getAllOrder().collectLatest {
                when(it){
                    is ResultState.Error -> {
                        _orderScreenState.value = OrderScreenState(error = it.message)
                    }
                    ResultState.Loading -> {
                        _orderScreenState.value = OrderScreenState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        Log.d("THis is order viewmodel",it.data.data.toString())
                        _orderScreenState.value = OrderScreenState(data = it.data.data)
                    }
                }
            }
        }
    }
    fun updateOrder(order:OrderModel){
        viewModelScope.launch {
            repo.updateOrder(order = order).collectLatest {
                when(it){
                    is ResultState.Error -> {
                        _updateOrderScreenState.value = UpdateOrderState(errorMessage = it.message, error = true)
                    }
                    ResultState.Loading -> {
                        Log.d("THis is order viewmodel","updare fail order")
                        _updateOrderScreenState.value = UpdateOrderState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        getOrders()
                        _updateOrderScreenState.value = UpdateOrderState(success = it.data.success)
                    }
                }
            }
        }
    }
}