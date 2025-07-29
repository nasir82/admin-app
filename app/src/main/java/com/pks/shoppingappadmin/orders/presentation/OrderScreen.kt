package com.pks.shoppingappadmin.orders.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pks.shoppingappadmin.components.OrderCard
import com.pks.shoppingappadmin.components.ShoppingButton
import com.pks.shoppingappadmin.navigation.OrderDetails

@Composable
fun OrderScreenUi(navHostController: NavHostController,viewModel: OrderViewModel) {

    val state = viewModel.orderScreenState.collectAsState().value
    Box(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background)) {
        if(state.isLoading){
            Column (modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                CircularProgressIndicator()
            }
        }else if(state.error.isNotEmpty()){
            Column (modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
               Text(text = state.error.toString())
                Spacer(modifier = Modifier.height(16.dp))
                ShoppingButton(text = "Try again", modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 3.dp)
                    .wrapContentWidth()) {

                }
            }
        }
        else{
        val orders = state.data
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Spacer(modifier = Modifier.height(40.dp))


            Text(
                text = "Pending orders",
                style =MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (orders.isEmpty()) {
                Text(text = "No orders available", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(orders) {order->
                        OrderCard(order = order) {
                            Log.d("We are ready to navigate","Let's go ${order.receipt.toString()}")
                            navHostController.navigate((OrderDetails(order = order)))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))

            }
        }
            }
    }
}