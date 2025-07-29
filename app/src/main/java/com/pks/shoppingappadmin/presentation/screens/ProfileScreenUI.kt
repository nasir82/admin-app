package com.pks.shoppingappadmin.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingappadmin.R
import com.pks.shoppingappadmin.components.ShoppingButton
import com.pks.shoppingappadmin.components.ShoppingTextField2
import com.pks.shoppingappadmin.presentation.navigation.Login
import com.pks.shoppingappadmin.presentation.viewmodels.ShoppingAppViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreeUI(
    modifier: Modifier = Modifier,
    viewModel: ShoppingAppViewModel = hiltViewModel(),
    nav:NavHostController,
    auth:FirebaseAuth
) {

    val x = LocalConfiguration.current.screenWidthDp - 150
    val y = LocalConfiguration.current.screenHeightDp - 80

    val state = viewModel.profileScreenState.collectAsState()
    if (state.value.isLoading) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (state.value.errorMessage != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = state.value.errorMessage.toString())
        }
    } else {
        val nameParts = remember {
            state.value.userData?.name?.let {
                it.split(" ")
            } ?: listOf("", "")
        }

        val fName = remember { mutableStateOf(nameParts.first()) }
        val lName = remember { mutableStateOf(nameParts.getOrNull(1) ?: "") }
        val email = remember {
            state.value.userData?.email?.let {
                mutableStateOf(it)
            }?: mutableStateOf("")
        }
        val password = remember {
            state.value.userData?.password?.let {
                mutableStateOf(it)
            }?: mutableStateOf("")
        }
        val confirmPassword = remember {
            mutableStateOf("No address")
        }
        val isEnable = remember {
            mutableStateOf(false)
        }
        val isAnimate = remember {
            mutableStateOf(false)
        }
        val btnText = if (isEnable.value) "Save" else "Edit Profile"
        val focusManager = LocalFocusManager.current
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier

                    .offset(x = (x).dp, y = (-200).dp)
                    .background(color = Color(0xFFF68B8B), shape = CircleShape)
                    .size(size = 350.dp)
                    .clip(
                        shape = CircleShape
                    )
            )
            Box(
                modifier = Modifier

                    .offset(x = (-160).dp, y = (y).dp)
                    .background(color = Color(0xFFF68B8B), shape = CircleShape)
                    .size(size = 250.dp)
                    .clip(
                        shape = CircleShape
                    )
            )

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {


                Image(
                    painterResource(id = R.drawable.profile),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(shape = CircleShape)
                        .border(width = 2.dp, color = Color(0xFFF68B8B), shape = CircleShape)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    ShoppingTextField2(
                        label = "First Name",
                        isEnable = isEnable.value,
                        value = fName.value,
                        modifier = Modifier.weight(1f)
                    ) {
                        fName.value = it
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    ShoppingTextField2(
                        label = "Last Name",
                        isEnable = isEnable.value,
                        value = lName.value,
                        modifier = Modifier.weight(1f)
                    ) {
                        lName.value = it
                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                ShoppingTextField2(label = "Email", value = email.value, isEnable = isEnable.value) {
                    email.value = it
                }
                Spacer(modifier = Modifier.height(16.dp))
                ShoppingTextField2(
                    label = "Phone",
                    value = password.value,
                    isEnable = isEnable.value
                ) {
                    password.value = it
                }
                Spacer(modifier = Modifier.height(16.dp))
                ShoppingTextField2(
                    label = "Address",
                    isEnable = isEnable.value,
                    value = confirmPassword.value
                ) {
                    confirmPassword.value = it
                }

                if(isAnimate.value) {
                    ShoppingAlertDialog(onCancel = { isAnimate.value = !isAnimate.value }) {
                        auth.signOut().let {
                            isAnimate.value = !isAnimate.value
                            nav.navigate(Login)
                        }

                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                ShoppingButton(text = "Logout", containerColor = Color(0xFFF68B8B)) {
                    // nav.navigate(NavDestinations.LogOut)
                    isAnimate.value = !isAnimate.value
                }

                Spacer(modifier = Modifier.height(24.dp))
                ShoppingButton(text = btnText, containerColor = Color(0xFFD9D9D9)) {
                    focusManager.clearFocus()
                    isEnable.value = !isEnable.value
                }


            }

        }
    }
}


@Composable
fun ShoppingAlertDialog(modifier: Modifier = Modifier, onCancel: () -> Unit, onConfirm: () -> Unit) {

    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            ShoppingButton(text = "Log out", modifier = Modifier.wrapContentWidth()) {
                onConfirm.invoke()
            }
        },
        dismissButton = {
            ShoppingButton(text = "Cancel", modifier = Modifier.wrapContentWidth()) {
                onCancel.invoke()
            }
        },
        shape = RoundedCornerShape(15.dp),
        title = {


            Column(
                modifier = Modifier
                    .height(250.dp)
                    .width(350.dp)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                Image(
                    painterResource(id = R.drawable.profile),
                    contentDescription = "",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Crop

                )

                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Log Out", color = Color(0xFFF68B8B))
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Do you really want to logout?",
                    textAlign = TextAlign.Center
                )
            }


        })


}
