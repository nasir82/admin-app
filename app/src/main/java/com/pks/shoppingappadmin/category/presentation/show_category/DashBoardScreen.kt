package com.pks.shoppingappadmin.category.presentation.show_category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pks.shoppingappadmin.category.presentation.AddCategoryViewModel
import com.pks.shoppingappadmin.components.CategoryCart
import com.pks.shoppingappadmin.core.presentation.utils.shimmerEffect
import com.pks.shoppingappadmin.navigation.AllProducts


@Composable
fun DashBoardScreenUi(
    viewModel: AddCategoryViewModel,
    nav: NavHostController
) {
    val state = viewModel.categoryState2.collectAsState().value
    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = 8.dp)) {
                Spacer(modifier = Modifier.height(50.dp))
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(6) { pos ->
                        val padding =
                            if (pos % 2 == 1) PaddingValues(start = 5.dp) else PaddingValues(end = 5.dp)
                        Column(
                            modifier = Modifier
                                .height(260.dp)
                                .fillMaxWidth()
                                .padding(padding)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(220.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .shimmerEffect()
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(24.dp)
                                    .clip(shape = RoundedCornerShape(5.dp))
                                    .shimmerEffect()
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(90.dp))
            }
        }
    } else if (state.error.isNotEmpty()) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text(text = state.error)
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(horizontal = 8.dp)
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "All Categories", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(10.dp))
                state.data?.let { list ->
                    var left = true
                    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.weight(1f)) {
                        items(list) { item ->
                            val paddingValues =
                                if (left) PaddingValues(end = 5.dp) else PaddingValues(start = 5.dp)
                            left = !left
                            Column(modifier = Modifier.padding(paddingValues)) {
                                CategoryCart(item = item) {
                                    nav.navigate(AllProducts(category = item.name))
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(80.dp))
                } ?: Text(text = "No Categories Available")
            }
        }
    }
}