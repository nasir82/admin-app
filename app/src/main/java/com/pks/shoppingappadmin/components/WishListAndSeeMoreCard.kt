package com.pks.shoppingappadmin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pks.shoppingappadmin.products.domain.model.ProductModel


@Composable
fun WishListAndSeeMoreCard(modifier: Modifier = Modifier, product: ProductModel, onClick:()->Unit={}) {
    Box(modifier = Modifier.fillMaxWidth().height(125.dp)){


        Row(modifier = Modifier.fillMaxSize().padding(bottom = 10.dp).clickable { onClick.invoke() }) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.thumbnail)
                    .crossfade(true) // Enables crossfade animation
                    .build(),
                contentDescription = "",
                modifier = Modifier.width(75.dp).clip(shape = RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.width(5.dp))
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = product.title,
                        maxLines = 2,
                       style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(text = product.categoryId,color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.labelMedium)
                    Text(text = product.productType,color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.labelMedium)
                    Text(text = "Blue",color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.labelMedium)

                }
            }

            Row(modifier = Modifier.width(80.dp), horizontalArrangement = Arrangement.End) {
                Text(text =product.price.toString(), style = MaterialTheme.typography.titleMedium,color = MaterialTheme.colorScheme.onBackground)
            }
            Spacer(modifier = Modifier.width(5.dp))

            Row(modifier = Modifier.width(30.dp), horizontalArrangement = Arrangement.End) {

            }

        }
    }
}