package com.pks.shoppingappadmin.products.presentation.all_products

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pks.shoppingappadmin.components.ShoppingTextField
import com.pks.shoppingappadmin.components.WishListAndSeeMoreCard
import com.pks.shoppingappadmin.products.presentation.AddProductViewModel


@Composable
fun AllProductScreen(modifier: Modifier = Modifier,nav:NavHostController,category:String,viewModel: AddProductViewModel) {

    Box(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background)) {

        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = category, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(8.dp)
                        .clickable {
                            nav.popBackStack()
                        }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "See your favourite one", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onBackground)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "items", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onBackground )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Price",style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onBackground)
                Row(modifier = Modifier.width(30.dp)) {

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShoppingTextField(
                    label = "",
                    value = "Search",
                    modifier = Modifier.weight(1f),
                    leadingIcon = Icons.Default.Search,
                    onLeadingClick = {},
                    trailingIcon = Icons.Default.Clear,
                    onTrailingClick = {

                    }) {

                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(viewModel.productState.value.data!!.filter {
                    it.categoryName == category
                }) {
                    product->
                    WishListAndSeeMoreCard(product = product){
                    }
                }

            }

            Spacer(modifier = Modifier.height(80.dp))

        }
    }
}