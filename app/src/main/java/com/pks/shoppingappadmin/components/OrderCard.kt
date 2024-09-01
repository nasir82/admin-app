package com.pks.shoppingappadmin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun OrderCard(modifier: Modifier = Modifier,onClick:()-> Unit = {}) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(bottom = 8.dp)
            .clickable {
                       onClick.invoke()
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF),
            disabledContainerColor =Color(0xFFFFFFFF)
        )
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(vertical =  10.dp, horizontal = 16.dp)) {
            Text(text = "4lk34jl4522525", fontSize = 18.sp, fontWeight = FontWeight.W600)
            Text(text = "Order received")
            Text(text = "Varsity gate, Akhalia, Sylhet", fontWeight = FontWeight.Bold)
            Text(text = "AProximate date : 2 to 5 September")
        }

    }
}
