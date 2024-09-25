package com.pks.shoppingappadmin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pks.shoppingappadmin.components.ShoppingButton
import com.pks.shoppingappadmin.components.ShoppingTextField


@Preview(showBackground = true)
@Composable
fun BrandInfo(modifier: Modifier = Modifier) {

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(modifier = Modifier.fillMaxWidth()) {
            ShoppingTextField(label = "BrandId", value = "", modifier = Modifier.weight(1f)) {

            }
            Spacer(modifier = Modifier.width(12.dp))
            ShoppingTextField(label = "BrandName", value = "", modifier = Modifier.weight(1f)) {

            }


        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            ShoppingTextField(label = "ProductCount", value = "", modifier = Modifier.weight(1f)) {

            }
            Spacer(modifier = Modifier.width(12.dp))
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "isFeatured: ")
                Checkbox(checked = true, onCheckedChange = {})
            }


        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            ShoppingTextField(label = "Brand Logo", value = "", modifier = Modifier.weight(1f)) {

            }
            Spacer(modifier = Modifier.width(12.dp))
            ShoppingButton(text = "Add", modifier = Modifier.wrapContentWidth()) {

            }

        }

    }

}


@Preview(showBackground = true)
@Composable
fun AddAttributes(modifier: Modifier = Modifier) {
    var ite = mutableListOf<String>("nasir", "basir")
    Column(modifier = Modifier.fillMaxWidth()) {
        ShoppingTextField(label = "Name", value = "") {

        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.heightIn(min = 30.dp, max = 250.dp)
        ) {
            items(ite) {
                Text(text = it)
            }
        }
        ShoppingTextField(label = "Enter", value = "") {

        }
    }

}




@Composable
fun AddVariation(modifier: Modifier = Modifier) {
    Column {


    }
}

//data class BrandModel(
//
//    var id: String="",
//    var name: String="",
//    var image: String="",
//    var isFeatured: Boolean=false,
//    var productCount: Int=0
//)

@Preview(showBackground = true)
@Composable
fun VariationInfo(modifier: Modifier = Modifier) {

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(modifier = Modifier.fillMaxWidth()) {
            ShoppingTextField(label = "Id", value = "", modifier = Modifier.weight(1f)) {

            }
            Spacer(modifier = Modifier.width(12.dp))
            ShoppingTextField(label = "SKU", value = "", modifier = Modifier.weight(1f)) {

            }


        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            ShoppingTextField(label = "Price", value = "", modifier = Modifier.weight(1f)) {

            }
            Spacer(modifier = Modifier.width(12.dp))
            ShoppingTextField(label = "SalePrice", value = "", modifier = Modifier.weight(1f)) {

            }
            Spacer(modifier = Modifier.width(12.dp))
            ShoppingTextField(label = "Stock", value = "", modifier = Modifier.weight(1f)) {

            }


        }
        Spacer(modifier = Modifier.height(8.dp))

        ShoppingTextField(label = "Description", value = "") {

        }


        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            ShoppingTextField(label = "Image", value = "", modifier = Modifier.weight(1f)) {

            }
            Spacer(modifier = Modifier.width(12.dp))
            ShoppingButton(text = "Add", modifier = Modifier.wrapContentWidth()) {

            }

        }
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            ShoppingTextField(label = "Image", value = "", modifier = Modifier.weight(1f)) {

            }
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "To")
            Spacer(modifier = Modifier.width(6.dp))
            ShoppingTextField(label = "Image", value = "", modifier = Modifier.weight(1f)) {

            }
        }

    }

}
//data class ProductVariationsModel(
//
//    var id:String="",
//    var sku: String="",
//    var image: String="",
//    var description: String="",
//    var price: Double=0.0,
//    var salePrice: Double=0.0,
//    var stock: Int=0,
//    var attributeValues:Map<String,String> = mutableMapOf()
//
//)