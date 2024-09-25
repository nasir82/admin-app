package com.pks.shoppingappadmin.category.presentation.show_category

import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pks.shoppingappadmin.category.presentation.AddCategoryViewModel
import com.pks.shoppingappadmin.components.CategoryCart
import com.pks.shoppingappadmin.core.presentation.utils.shimmerEffect
import com.pks.shoppingappadmin.presentation.navigation.AllProducts


@Composable
fun DashBoardScreenUi(
    modifier: Modifier = Modifier,
    viewModel: AddCategoryViewModel,
    nav: NavHostController
) {
    val state = viewModel.categoryState2.collectAsState().value
    //val productState = viewModel.productState.collectAsState(initial = null)
    if (state.isLoading) {

        Box(modifier = Modifier.fillMaxSize()) {

            Column(modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp)) {
                Spacer(modifier = Modifier.height(60.dp))
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(6) {pos->
                        val padding = if(pos%2==1) PaddingValues(start = 5.dp) else PaddingValues(end = 5.dp)
                        Column(
                            modifier = Modifier
                                .height(280.dp)
                                .fillMaxWidth()
                                .padding(padding)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(240.dp)
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
        Text(text = state.error)
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFF9F9F9)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = "All Categories", style = TextStyle(
                    fontWeight = FontWeight.SemiBold, fontSize = 24.sp
                )
                )
                Spacer(modifier = Modifier.height(10.dp))
//                if (viewModel.statusList.value.isEmpty()) {
//                    Text(text = "No Category to show")
//                } else

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
                } ?: Text(text = "Empty")
            }
        }
    }
}