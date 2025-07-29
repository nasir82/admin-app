package com.pks.shoppingappadmin.category.presentation.add_category

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.pks.shoppingappadmin.category.presentation.AddCategoryState
import com.pks.shoppingappadmin.category.presentation.AddCategoryViewModel
import com.pks.shoppingappadmin.components.ShoppingAppSnackBar
import com.pks.shoppingappadmin.components.ShoppingButton
import com.pks.shoppingappadmin.components.ShoppingTextField
import com.pks.shoppingappadmin.core.domain.isNetworkAvailable
import com.pks.shoppingappadmin.core.presentation.utils.LoadingScreen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Composable
fun AddCategoryScreenUi(
    viewModel: AddCategoryViewModel,
    navController:NavHostController
) {
    val name = remember {
        mutableStateOf("")
    }
    val parentId = remember {
        mutableStateOf("")
    }
    val isFeatured = remember {
        mutableStateOf(false)
    }


    val image = remember {
        mutableStateOf("")
    }
    val message = remember {
        mutableStateOf("Cheking")
    }
    val code = remember {
        mutableIntStateOf(0)
    }
    val snackBarState = remember {
        SnackbarHostState()
    }
    val context = LocalContext.current
    Scaffold(
        snackbarHost = {

            ShoppingAppSnackBar(
                state = snackBarState,
                message = message.value,
                code = code.value
            )
        }
    ) { innerPadding ->
        innerPadding
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(top =  innerPadding.calculateTopPadding() - 10.dp, bottom = innerPadding.calculateBottomPadding())
        ) {


            val laucher =
                rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
                    if (it != null) image.value = it.toString()
                    else image.value = ""
                }
            //Spacer(modifier = Modifier.height(50.dp))

            val state = viewModel._addCategoryState.collectAsState().value
            if (state.isLoading) {
                LoadingScreen(text = "Processing")
            } else {

                if (state.error.isNotEmpty()) {
                    Log.d("calling upload", "thi checking")
                    message.value = state.error.toString()
                    code.value = 0
//                    snake()
                    checkingFrom(snackBarState,viewModel)
                }

                if (state.success.isNotEmpty()) {
                    message.value = "Added the new category"
                    code.value = 2
                    // here i need to call snackbar how can i do this
                    checkingFrom(snackBarState,viewModel)
                }


                Text(text = "Add new category", modifier = Modifier.padding(start = 8.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Transparent)
                        .height(220.dp)
                        .padding(horizontal = 8.dp)
                        .clip(shape =RoundedCornerShape(10.dp) )
                        .border(color = Color.DarkGray, width = 1.dp, shape = RoundedCornerShape(10.dp))
                        .clickable {
                            laucher.launch("image/*")
                        }, contentAlignment = Alignment.Center
                ) {
                    if (image.value.isEmpty())
                        Text(
                            text = "Add Icon Image",
                            fontWeight = FontWeight.W900,
                            fontSize = 36.sp,
                            color = Color.Black.copy(alpha = 0.3f)
                        )
                    else {
                        val paint = rememberAsyncImagePainter(model = image.value)
                        Image(
                            painter = paint,
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    //  Text(text = "Loaded", fontWeight = FontWeight.W900, fontSize = 36.sp, color = Color.Black.copy(alpha = 0.3f))


                }
                Spacer(modifier = Modifier.height(10.dp))
                ShoppingTextField(
                    label = "Category Name",
                    value = name.value,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    name.value = it
                }
                ShoppingTextField(
                    label = "ParentId",
                    value = parentId.value,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    parentId.value = it
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Is Featured: ")
                    Checkbox(checked = isFeatured.value, onCheckedChange = {
                        isFeatured.value = !isFeatured.value
                    })
                }
//            ShoppingAppSnackBar(
//                state = snackBarState, message = "this is message"
//            )

                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp), horizontalArrangement = Arrangement.End
                ) {
//                    ShoppingButton(text = "Cancel", modifier = Modifier.wrapContentWidth()) {
//                        navController.popBackStack()
//                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    ShoppingButton(
                        text = "Add",
                        modifier = Modifier.wrapContentWidth(),
                        containerColor = Color(0xFF64e3a1)
                    ) {
                        if (image.value == null) Log.d("stringvalue", image.value)
                        viewModel.addCategory(
                            image.value,
                            name.value,
                            parentId.value,
                            isFeatured.value,
                            isNetworkAvailable(context)
                        )
                    }
                }


            }

        }
    }
}

fun uploadImage(image: String) {

}


fun checkingFrom(state: SnackbarHostState,viewModel: AddCategoryViewModel) {
    GlobalScope.launch {

        state.showSnackbar(
            message = ""
        )
        viewModel._addCategoryState.value = AddCategoryState()
    }

}


