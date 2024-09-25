package com.pks.shoppingappadmin.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pks.shoppingappadmin.category.presentation.AddCategoryViewModel

@Preview(showBackground = true)
@Composable
fun CategoryScreen(modifier: Modifier = Modifier,viewModel: AddCategoryViewModel = hiltViewModel()) {

    val state = viewModel._addCategoryState.collectAsState().value
    val categoryName = remember{
        mutableStateOf("")
    }
    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    } else if (state.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = state.error)
        }
    } else {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = categoryName.value, onValueChange = {
                categoryName.value = it
            })
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                viewModel.category.value.name = categoryName.value
                //viewModel.addCategory()
            }) {
                Text(text = "submit")
            }
        }
    }
}