package com.pks.shoppingappadmin.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pks.shoppingappadmin.R
import com.pks.shoppingappadmin.category.domain.model.CategoryModel


@Composable
fun CategoryCart(modifier: Modifier = Modifier, item: CategoryModel, onClick: () -> Unit = {}) {
    Card(modifier = modifier
        .fillMaxWidth()
        //.background(color = Color(0xFFBDBDBD), shape = RoundedCornerShape(12.dp))
        .clickable {
            onClick.invoke()
        }
        .height(250.dp), shape = RoundedCornerShape(10.dp), colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surface,
        disabledContainerColor = Color.Transparent
    ),
        elevation = CardDefaults.elevatedCardElevation(2.dp)) {
        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.image)
                    .crossfade(true) // Enables crossfade animation
                    .build(),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth().height(210.dp).clip(shape = RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.name, modifier = Modifier.padding(horizontal = 8.dp), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(4.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AllProductCart(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp), shape = RoundedCornerShape(15.dp), colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        elevation = CardDefaults.elevatedCardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Image(
                painterResource(id = R.drawable.passion),
                contentDescription = "",
                modifier = Modifier.weight(1f),
                contentScale = ContentScale.Crop
            )
            Text(text = "Name of product category")
            Text(text = "Name of product category")
        }

    }
}