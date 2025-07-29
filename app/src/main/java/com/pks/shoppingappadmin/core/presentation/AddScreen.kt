package com.pks.shoppingappadmin.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pks.shoppingappadmin.R
import com.pks.shoppingappadmin.category.presentation.AddCategoryViewModel
import com.pks.shoppingappadmin.navigation.AddCategory
import com.pks.shoppingappadmin.navigation.AddProducts


@Composable
fun AddScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: AddCategoryViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Add Product or Category",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .padding(4.dp)
            .background(
                color = Color(0xFFCAD3C8),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable {
                navHostController.navigate(AddCategory)
            }
            .clip(shape = RoundedCornerShape(15.dp))) {
            Image(
                painterResource(id = R.drawable.add_category),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Add categories to increase diversity",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Yellow.copy(alpha = .69f)
                    ),
                    modifier = Modifier.padding(bottom = 15.dp)
                )
            }

        }
        Spacer(modifier = Modifier.height(1.dp))
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .padding(4.dp)
            .background(
                color = Color(0xFFCAD3C8),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable {
                navHostController.navigate(AddProducts)
            }
            .clip(shape = RoundedCornerShape(15.dp))) {
            Image(
                painterResource(id = R.drawable.addproduct),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Add products to enhance market",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Green
                    ),
                    modifier = Modifier.padding(bottom = 15.dp)
                )
            }
        }
//        ShoppingButton(text = "Add product") {
//
//        }

        Spacer(modifier = Modifier.height(100.dp))

    }
}