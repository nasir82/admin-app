package com.pks.shoppingappadmin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pks.shoppingappadmin.orders.domain.model.OrderModel
import com.pks.shoppingappadmin.orders.domain.model.getStatus


@Preview(showBackground = true)
@Composable
fun OrderCard(
    modifier: Modifier = Modifier,
    order: OrderModel = OrderModel(),
    onClick: () -> Unit = {}
) {

    val orderStatus = if(order.status==null) "__" else getStatus(order.status!!)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 8.dp)
            .clickable {
                onClick.invoke()
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF),
            disabledContainerColor = Color(0xFFFFFFFF)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 10.dp, horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "Order ID: ")
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = order.id ?: "__",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600
                )
            }
            Row {
                Text(text = "Order Status: ")
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = orderStatus, modifier = Modifier.weight(1f))
            }
            Row {
                Text(text = "Delivery Location: ")
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = order.location ?: "__", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            }
            Row {
                Text(text = "Approximate Del.: ")
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = order.approximateDelivery ?: "__", modifier = Modifier.weight(1f))
            }
            if(!order.deliveredBy.isNullOrEmpty()){
                Row {
                    Text(text = "Delivered By: ")
                    Spacer(modifier = Modifier.width(5.dp))

                        Text(text = order.deliveredBy)
                }
            }
        }

    }
}
