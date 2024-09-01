package com.pks.shoppingappadmin.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pks.shoppingappadmin.components.OrderCard
import com.pks.shoppingappadmin.presentation.navigation.OrderDetails

@Preview(showBackground = true)
@Composable
fun OrderScreenUi(modifier: Modifier = Modifier,navHostController: NavHostController= rememberNavController()) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFFF9F9F9))) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(text = "Pending orders", fontWeight = FontWeight.W600, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 8.dp))
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn (modifier = Modifier.weight(1f)){
            items(30){
                OrderCard(){
                    navHostController.navigate(OrderDetails)
                }
            }
        }
        Spacer(modifier = Modifier.height(50.dp))

    }
}