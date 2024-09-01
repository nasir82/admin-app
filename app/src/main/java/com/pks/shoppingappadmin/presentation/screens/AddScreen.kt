package com.pks.shoppingappadmin.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pks.shoppingappadmin.components.ShoppingButton
import com.pks.shoppingappadmin.presentation.navigation.AddCategory
import com.pks.shoppingappadmin.presentation.navigation.AddProducts


@Preview(showBackground = true)
@Composable
fun AddScreen(modifier: Modifier = Modifier,navHostController: NavHostController) {
    Column (modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)){
        Spacer(modifier = Modifier.height(60.dp))
        Text(text = "add Product or category")
        Spacer(modifier = Modifier.height(10.dp))
        ShoppingButton(text = "Add Category") {
            navHostController.navigate(AddCategory)
        }
        Spacer(modifier = Modifier.height(20.dp))
        ShoppingButton(text = "Add product") {
            navHostController.navigate(AddProducts)
        }

    }
}