package com.pks.shoppingappadmin.addproduct.presentation

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingappadmin.components.SectionHeading
import com.pks.shoppingappadmin.components.ShoppingAppSnackBar
import com.pks.shoppingappadmin.components.ShoppingButton
import com.pks.shoppingappadmin.components.ShoppingTextField
import com.pks.shoppingappadmin.core.domain.utils.isValidNumber
import com.pks.shoppingappadmin.domain.model.BrandModel
import com.pks.shoppingappadmin.domain.model.ProductAttributeModel
import com.pks.shoppingappadmin.domain.model.ProductModel
import com.pks.shoppingappadmin.domain.model.ProductVariationsModel
import com.pks.shoppingappadmin.orders.domain.model.orderList
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(DelicateCoroutinesApi::class)
@Preview(showBackground = true)
@Composable
fun AddProductsScreenUi(
    modifier: Modifier = Modifier,
    viewModel: AddProductViewModel = hiltViewModel()
) {
    val state = viewModel.addProductScreenState.collectAsState().value
    val attributeValue = remember {
        mutableStateOf("")
    }
    val ite = remember {
        mutableStateListOf<String>()
    }

    val product = viewModel.product
    val title = remember {
        mutableStateOf("")
    }
    val price = remember {
        mutableStateOf("")
    }
    val salePrice = remember {
        mutableStateOf("")
    }
    val isFeatured = remember {
        mutableStateOf("")
    }
    val isFeaturedBrand = remember {
        mutableStateOf(false)
    }
    val isVisible = remember {
        mutableStateOf(false)
    }
    val productType = remember {
        mutableStateOf("")
    }
    val categoryName = remember {
        mutableStateOf("")
    }
    val descriptions = remember {
        mutableStateOf("")
    }
    val stock = remember {
        mutableStateOf("")
    }
    val brandId = remember {
        mutableStateOf("")
    }
    val brandName = remember {
        mutableStateOf("")
    }
    val brandProductCount = remember {
        mutableStateOf("")
    }
    val brandFeatured = remember {
        mutableStateOf("")
    }
    val brandLogo = remember {
        mutableStateOf("")
    }
    val attributeName = remember {
        mutableStateOf("")
    }
    val variationId = remember {
        mutableStateOf("")
    }
    val variationSku = remember {
        mutableStateOf("")
    }
    val variationPrice = remember {
        mutableStateOf("")
    }
    val variationSalePrice = remember {
        mutableStateOf("")
    }

    val variationStock = remember {
        mutableStateOf("")
    }
    val variationDescription = remember {
        mutableStateOf("")
    }
    val variationImage = remember {
        mutableStateOf("")
    }
    val variationKey = remember {
        mutableStateOf("")
    }
    val variationValue = remember {
        mutableStateOf("")
    }
    val message = remember {
        mutableStateOf("")
    }
    val code = remember {
        mutableIntStateOf(0)
    }
    val thumbnailImage = remember {
        mutableStateOf("")
    }

    var coroutine = rememberCoroutineScope()

    val map = remember { mutableStateMapOf<String, String>() }
    var ls = mutableListOf<String>()
    var images = remember {
        mutableStateListOf<String>()
    }
    val snackBarState = remember {
        SnackbarHostState()
    }
    val attributeList = remember {
        mutableStateListOf<ProductAttributeModel>()
    }
    val variationList = remember {
        mutableStateListOf<ProductVariationsModel>()
    }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents()) {
            it.forEach { uri ->
                Log.d("Checked", "${ls.size}")
                Log.d("This is uri ", "uri of image")
                ls.add(uri.toString())
            }

        }

    Scaffold(
        snackbarHost = {
            ShoppingAppSnackBar(
                state = snackBarState,
                message = message.value,
                code = code.value
            )
        }
    ) { innerPad ->
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPad)
                    .verticalScroll(rememberScrollState())
            ) {

                Text(text = "Add New Products", modifier = Modifier.padding(horizontal = 8.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .clickable {
                            coroutine.launch {
                                launcher.launch("image/*")
                                while (ls.isEmpty()) {
                                    delay(10)
                                }
                                if (ls.isNotEmpty()) {
                                    Log.d("Checked", "${ls.size}")
                                    thumbnailImage.value = ls[0]
                                    ls.clear()
                                }

                            }

                        }
                        .height(220.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .border(width = 1.dp, color = Color.DarkGray, shape =  RoundedCornerShape(10.dp)),

                    contentAlignment = Alignment.Center
                ) {
                    if (thumbnailImage.value.isBlank())
                        Text(text = "Select an Image", fontSize = 40.sp)
                    else {
                        val paint = rememberAsyncImagePainter(model = thumbnailImage.value.toUri())

                        Image(
                            painter = paint,
                            contentDescription = "",
                            modifier = Modifier.size(115.dp),
                            contentScale = ContentScale.FillBounds
                        )

                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {


                    ShoppingTextField(label = "Title", value = title.value) {
                        title.value = it
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {

                        Column(modifier = Modifier.weight(1f)) {
                            ShoppingTextField(
                                label = "Price",
                                value = price.value,
                                isError = isValidNumber(price.value),
                                keyboardType = KeyboardType.Number
                            ) {


                                price.value = it

                            }

                            if (isValidNumber(price.value) and price.value.isNotEmpty())
                                Text(text = "Enter valid number")

                        }

                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            ShoppingTextField(
                                label = "SalePrice",
                                value = salePrice.value,
                                isError = isValidNumber(salePrice.value),
                                keyboardType = KeyboardType.Number
                            ) {
                                salePrice.value = it
                            }
                            if (isValidNumber(salePrice.value) and salePrice.value.isNotEmpty())
                                Text(text = "Enter valid number")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        ShoppingTextField(
                            label = "isFeatured",
                            value = isFeatured.value,
                            modifier = Modifier.weight(1f),
                            trailingIcon = Icons.Default.ArrowDropDown
                        ) {
                            isFeatured.value = it
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        ShoppingTextField(
                            label = "ProductType",
                            value = productType.value,
                            modifier = Modifier.weight(1f),
                            trailingIcon = Icons.Default.ArrowDropDown
                        ) {
                            productType.value = it
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    ShoppingTextField(label = "CategoryName", value = "") {

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    ShoppingTextField(label = "Description", value = descriptions.value) {
                        descriptions.value = it
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    ShoppingTextField(
                        label = "Stock",
                        value = stock.value,
                        isError = isValidNumber(stock.value),
                        keyboardType = KeyboardType.Number
                    ) {
                        stock.value = it
                    }
                    if (isValidNumber(stock.value) and stock.value.isNotEmpty())
                        Text(text = "Enter valid number")
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(10.dp))
                            .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(10.dp))
                            .clickable {

                                coroutine.launch {
                                    launcher.launch("image/*")
                                    while (ls.isEmpty()) {
                                        delay(10)
                                    }
                                    images.addAll(ls)
                                    ls.clear()
                                }

                            }
                            .height(120.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (images.isEmpty())
                            Text(text = "Select Images", fontSize = 40.sp)
                        else {
                            LazyRow(
                                horizontalArrangement = Arrangement.Start
                            ) {
                                items(images.toList()) {

                                    val paint = rememberAsyncImagePainter(model = it.toUri())
                                    Column(modifier = Modifier.padding(end = 10.dp)) {
                                        Image(
                                            painter = paint,
                                            contentDescription = "",
                                            modifier = Modifier.size(115.dp),
                                            contentScale = ContentScale.FillBounds
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    SectionHeading(title = "Brand Info", text = "")
                    Column(modifier = Modifier.fillMaxWidth()) {

                        Row(modifier = Modifier.fillMaxWidth()) {
                            ShoppingTextField(
                                label = "BrandId",
                                value = brandId.value,
                                modifier = Modifier.weight(1f)
                            ) {
                                brandId.value = it
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            ShoppingTextField(
                                label = "BrandName",
                                value = brandName.value,
                                modifier = Modifier.weight(1f)
                            ) {
                                brandName.value = it
                            }


                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                ShoppingTextField(
                                    label = "ProductCount",
                                    isError = isValidNumber(brandProductCount.value),
                                    value = brandProductCount.value
                                ) {
                                    brandProductCount.value = it
                                }
                                if (isValidNumber(brandProductCount.value) and brandProductCount.value.isNotEmpty())
                                    Text(text = "Enter valid number")
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Spacer(modifier = Modifier.weight(1f))
                                Text(text = "isFeatured: ")
                                Checkbox(
                                    checked = isFeaturedBrand.value,
                                    onCheckedChange = {
                                        isFeaturedBrand.value = !isFeaturedBrand.value
                                    })
                            }


                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ShoppingTextField(
                                label = "Brand Logo",
                                value = brandLogo.value,
                                modifier = Modifier.weight(1f),
                                readOnly = true
                            ) {
                                brandLogo.value = it
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            ShoppingButton(text = "Add", modifier = Modifier.wrapContentWidth()) {
                                coroutine.launch {
                                    launcher.launch("image/*")
                                    while (ls.isEmpty()) {
                                        delay(10)
                                    }
                                    brandLogo.value = ls[0]
                                    ls.clear()

                                }

                            }

                        }

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Products attribute")
                    //AddAttributes()

                    Column(modifier = Modifier.fillMaxWidth()) {

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 200.dp)
                        ) {
                            items(attributeList) {
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    Text(text = it.name)
                                    Text(text = it.values.joinToString(separator = ", "))
                                }
                            }
                        }
                        ShoppingTextField(label = "Name", value = attributeName.value) {
                            attributeName.value = it
                        }
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier.heightIn(min = 0.dp, max = 250.dp)
                        ) {
                            items(ite) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.padding(bottom = 5.dp)
                                ) {
                                    Text(
                                        text = it,
                                        modifier = Modifier.weight(1f),
                                        lineHeight = 14.sp
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    IconButton(onClick = { ite.remove(it) }) {
                                        Icon(
                                            imageVector = Icons.Rounded.Remove,
                                            contentDescription = "",
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ShoppingTextField(
                                label = "Enter value",
                                value = attributeValue.value,
                                modifier = Modifier.weight(1f)
                            ) {
                                attributeValue.value = it
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            ShoppingButton(text = "add", modifier = Modifier.wrapContentWidth()) {
                                if (attributeValue.value.isNotBlank()) {
                                    ite.add(attributeValue.value)
                                    // Clear the input field after adding
                                    attributeValue.value = ""
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        ShoppingButton(text = "add attribute") {
                            attributeList.add(
                                ProductAttributeModel(
                                    name = attributeName.value,
                                    values = ite.toList()
                                )
                            )
                            attributeName.value = ""
                            ite.clear()
                        }


                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    SectionHeading(title = "Product variations", text = "")
                    //VariationInfo()
                    Column(modifier = Modifier.fillMaxWidth()) {

                        LazyColumn(modifier = Modifier.heightIn(max = 200.dp)) {
                            items(variationList) {
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    Row(modifier = Modifier.fillMaxWidth()) {
                                        Text(
                                            "id: ${it.id}",
                                            modifier = Modifier.weight(1f)
                                        )
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Text(
                                            "sku ${it.sku}",
                                            modifier = Modifier.weight(1f)
                                        )


                                    }
                                    VerticalDivider(modifier = Modifier.padding(vertical = 2.dp))

                                    Row(modifier = Modifier.fillMaxWidth()) {
                                        Text(
                                            "price ${it.price}",
                                            modifier = Modifier.weight(1f)
                                        )
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Text(
                                            "SalePrice ${it.salePrice.toString()}",
                                            modifier = Modifier.weight(1f)
                                        )
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Text(
                                            "stock ${it.stock.toString()}",
                                            modifier = Modifier.weight(1f)
                                        )

                                    }
                                    HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
                                    Text(
                                        "description: \n${it.description}",
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
                                    Text(
                                        "image \n${it.image}",
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

                                    LazyVerticalGrid(
                                        columns = GridCells.Fixed(2),
                                        modifier = Modifier.heightIn(max = 50.dp)
                                    ) {
                                        Log.d("Printing map", "${it.attributeValues.size}")
                                        items(it.attributeValues.entries.toList()) {
                                            Text(text = "${it.key} : ${it.value}")
                                        }

                                    }

                                    Spacer(modifier = Modifier.height(10.dp))
                                }
                            }
                        }

                        Row(modifier = Modifier.fillMaxWidth()) {
                            ShoppingTextField(
                                label = "Id",
                                value = variationId.value,
                                modifier = Modifier.weight(1f)
                            ) {
                                variationId.value = it
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            ShoppingTextField(
                                label = "SKU",
                                value = variationSku.value,
                                modifier = Modifier.weight(1f)
                            ) {
                                variationSku.value = it
                            }


                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.weight(1f)) {
                                ShoppingTextField(
                                    label = "Price",
                                    isError = isValidNumber(variationPrice.value),
                                    value = variationPrice.value
                                ) {
                                    variationPrice.value = it
                                }
                                if (isValidNumber(variationPrice.value) and variationPrice.value.isNotEmpty())
                                    Text(text = "Enter valid number")
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                ShoppingTextField(
                                    label = "SalePrice",
                                    value = variationSalePrice.value,
                                    isError = isValidNumber(variationSalePrice.value)
                                ) {
                                    variationSalePrice.value = it
                                }

                                if (isValidNumber(variationSalePrice.value) and variationSalePrice.value.isNotEmpty())
                                    Text(text = "Enter a valid number")
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                ShoppingTextField(
                                    label = "Stock",
                                    value = variationStock.value,
                                    isError = isValidNumber(variationStock.value)
                                ) {
                                    variationStock.value = it
                                }
                                if (isValidNumber(variationStock.value) and variationStock.value.isNotEmpty())
                                    Text(text = "Enter a valid number")
                            }


                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        ShoppingTextField(
                            label = "Description",
                            value = variationDescription.value
                        ) {
                            variationDescription.value = it
                        }


                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ShoppingTextField(
                                label = "Image",
                                value = variationImage.value,
                                modifier = Modifier.weight(1f),
                                readOnly = true
                            ) {
                                variationImage.value = it
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            ShoppingButton(text = "Add", modifier = Modifier.wrapContentWidth()) {
                                coroutine.launch {
                                    launcher.launch("image/*")
                                    while (ls.isEmpty()) {
                                        delay(10)
                                    }
                                    variationImage.value = ls[0]
                                    ls.clear()

                                }
                            }

                        }

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.heightIn(max = 120.dp)
                        ) {
                            items(map.entries.toList()) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "${it.key} : ${it.value}",
                                        modifier = Modifier.weight(1f)
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    IconButton(onClick = { map.remove(it.key) }) {
                                        Icon(
                                            imageVector = Icons.Rounded.Remove,
                                            contentDescription = "",
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }

                                }

                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ShoppingTextField(
                                label = "Key",
                                value = variationKey.value,
                                modifier = Modifier.weight(1f)
                            ) {
                                variationKey.value = it
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "To")
                            Spacer(modifier = Modifier.width(6.dp))
                            ShoppingTextField(
                                label = "Value",
                                value = variationValue.value,
                                modifier = Modifier.weight(1f)
                            ) {
                                variationValue.value = it
                            }

                            IconButton(onClick = {
                                map[variationKey.value] = variationValue.value
                                variationKey.value = ""
                                variationValue.value = ""
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "",
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        ShoppingButton(text = "add to map") {
                            if (isValidNumber(variationPrice.value) || isValidNumber(
                                    variationSalePrice.value
                                ) || isValidNumber(variationStock.value)
                            ) {

                            } else {

                                val variation = ProductVariationsModel(
                                    id = variationId.value,
                                    sku = variationSku.value,
                                    image = variationImage.value,
                                    description = variationDescription.value,
                                    price = variationPrice.value.toDouble(),
                                    salePrice = variationSalePrice.value.toDouble(),
                                    stock = variationStock.value.toInt(),
                                    attributeValues = map.toMap()

                                )

                                variationList.add(variation)
                                variationId.value = ""
                                variationSku.value = ""
                                variationImage.value = ""
                                variationDescription.value = ""
                                variationPrice.value = ""
                                variationSalePrice.value = ""
                                variationStock.value = ""
                                map.clear()
                            }

                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    if (state.isLoading) {
                        Box(contentAlignment = Alignment.BottomCenter) {
                            Text(text = "loading....")
                        }
                    } else if (state.error.isNotEmpty()) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = state.error)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))


                    ShoppingButton(
                        text = "Upload"
                    ) {

                        if (isValidNumber(stock.value) || isValidNumber(price.value) || isValidNumber(
                                salePrice.value
                            ) || isValidNumber(brandProductCount.value)
                        ) {
                            val firestore = FirebaseFirestore.getInstance()

                            orderList.forEach {
                                firestore.collection("Orders").document(it.id!!).set(it)
                            }


                        } else {
                            // viewModel.uploadProduct()
                            val product = ProductModel(
                                stock = stock.value.toInt(),
                                sku = variationSku.value,
                                price = price.value.toDouble(),
                                salePrice = salePrice.value.toDouble(),
                                title = title.value,
                                thumbnail = thumbnailImage.value,
                                isFeatured = true,
                                images = images.toList(),
                                categoryId = "CategoryId",
                                productType = "SINGLE",
                                brand = BrandModel(
                                    id = brandId.value,
                                    name = brandName.value,
                                    image = brandLogo.value,
                                    isFeatured = isFeaturedBrand.value,
                                    productCount = brandProductCount.value.toInt()

                                ),
                                productAttribute = attributeList,
                                productVariation = variationList
                            )
                            isVisible.value = true
                            viewModel.addProduct(product)
                        }
                    }


                }


            }
        }
    }
}


@Composable
fun ShowProgress(viewModel: AddProductViewModel) {
    val state = viewModel.addProductScreenState.collectAsState().value
    if (state.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Loading.....")
        }
    } else if (state.error.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = state.error)
        }
    } else if (state.success.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = state.error)
        }
    }
}
