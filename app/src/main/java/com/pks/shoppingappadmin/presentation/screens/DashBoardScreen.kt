package com.pks.shoppingappadmin.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pks.shoppingappadmin.components.CategoryCart


@Preview(showBackground = true)
@Composable
fun DashBoardScreenUi(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize().background(color = Color(0xFFF9F9F9))){
        Column ( modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)){
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "All Categories")
            Spacer(modifier = Modifier.height(10.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.weight(1f)) {
                items(20){
                    val paddingValues = if(it%2==0) PaddingValues(end = 5.dp) else PaddingValues(start = 5.dp)
                    Column(modifier = Modifier.padding(paddingValues)) {
                        CategoryCart()
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                }
            }
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}