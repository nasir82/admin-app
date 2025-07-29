package com.pks.shoppingappadmin.orders.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingappadmin.components.ShoppingButton
import com.pks.shoppingappadmin.orders.domain.model.OrderModel
import com.pks.shoppingappadmin.orders.domain.model.OrderStatus
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState


@Composable
fun OrderDetailsScreenUI(modifier: Modifier = Modifier.fillMaxSize(), order: OrderModel,navHostController: NavHostController,viewModel: OrderViewModel) {
    val state = viewModel.updateOrderScreenState.collectAsState().value
        Column(modifier = modifier.background(color = MaterialTheme.colorScheme.background).padding(horizontal = 8.dp), verticalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Order Details: ", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
                Text(text = order.id ?: "", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
            }
            if (order.receipt.isNullOrEmpty()) {
                Text(text = "No order affiliated with this", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
            } else {
                
                Box(modifier = Modifier.weight(1f)) {

                    val pdfState = rememberVerticalPdfReaderState(
                        resource = ResourceType.Remote(order.receipt),
                        isZoomEnable = true
                    )
                    VerticalPDFReader(
                        state = pdfState, modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Transparent)
                    )

                }
                Row(
                    Modifier
                        .fillMaxWidth(), horizontalArrangement = Arrangement.End
                ) {
                    if (state.error) Dialog(onDismissRequest = { }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(150.dp), contentAlignment = Alignment.Center
                        ) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Icon(imageVector = Icons.Default.Info, contentDescription = "")
                                Text(text = state.errorMessage, modifier = Modifier.fillMaxWidth())
                            }
                        }
                    }
                    if (state.success) Dialog(onDismissRequest = { }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 150.dp), contentAlignment = Alignment.Center
                        ) {
                            Column(modifier = Modifier.wrapContentSize()) {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Icon(imageVector = Icons.Default.Info, contentDescription = "")
                                    Text(text = "Updated", modifier = Modifier.fillMaxWidth())
                                }

                                ShoppingButton(
                                    text = "Done", modifier = Modifier
                                        .wrapContentWidth()
                                        .align(Alignment.End)
                                ) {
                                    state.success = false
                                    navHostController.popBackStack()
                                }
                            }
                        }
                    }
                    ShoppingButton(
                        text = if (viewModel.updateOrderScreenState.collectAsState().value.isLoading) "Updating..." else "Reject",
                        modifier = Modifier.wrapContentWidth(),
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onBackground
                    ) {
                        // order_cancel logic
                        order.status = OrderStatus.REJECT.name
                        order.preparedBy = FirebaseAuth.getInstance().currentUser?.uid ?: " "
                        viewModel.updateOrder(order = order)

                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    ShoppingButton(
                        text = "Accept",
                        modifier = Modifier.wrapContentWidth(),
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onBackground
                    ) {
                        // accept order and move it to archive
                        order.status = OrderStatus.CONFIRM.name
                        order.preparedBy = FirebaseAuth.getInstance().currentUser?.uid ?: " "
                        viewModel.updateOrder(order = order)
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
        }

}

@Composable
fun CustomAlertDialogue(icon:ImageVector=Icons.Default.Info, message:String, onDismiss:()->Unit={}, onDone:()->Unit) {
    Dialog(onDismissRequest = {
        onDismiss.invoke()
    }) {
        Column(modifier = Modifier
            .height(150.dp)
            .fillMaxWidth(1f)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 4.dp), verticalArrangement = Arrangement.Center) {
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = icon, contentDescription = "")
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = message, modifier = Modifier.weight(1f), overflow = TextOverflow.Ellipsis)
                ShoppingButton(text = "Done", modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.Bottom)) {
                    onDone.invoke()

                }
            }
        }
    }
}