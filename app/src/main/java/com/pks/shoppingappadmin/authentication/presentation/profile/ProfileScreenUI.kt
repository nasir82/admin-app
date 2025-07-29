package com.pks.shoppingappadmin.authentication.presentation.profile

import android.util.Log
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingappadmin.R
import com.pks.shoppingappadmin.authentication.presentation.AuthenticationViewModel
import com.pks.shoppingappadmin.category.domain.model.UserData
import com.pks.shoppingappadmin.components.ShoppingButton
import com.pks.shoppingappadmin.components.ShoppingTextField2
import com.pks.shoppingappadmin.core.presentation.utils.shimmerEffect
import com.pks.shoppingappadmin.navigation.Login
import com.pks.shoppingappadmin.orders.presentation.CustomAlertDialogue


@Composable
fun ProfileScreeUI(
    state: State<ProfileScreenState>,
    nav: NavHostController,
    auth: FirebaseAuth,
    vm: AuthenticationViewModel
) {

    val x = LocalConfiguration.current.screenWidthDp - 150
    val y = LocalConfiguration.current.screenHeightDp - 80
    val uploadState = vm.userDataUploadState.collectAsState().value
    if (state.value.isLoading) {
        LoadingProfile()
        Log.d("Data is loading","from ui ")
    } else if (state.value.error.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = state.value.error.toString())
        }
    } else {
        Log.d("Data is loading","from ui else ${state.value.isLoading}")
        val nameParts = remember {
            state.value.data?.name?.let {
                it.split(" ")
            } ?: listOf("", "")
        }

        val firstName = if(nameParts.size>2) nameParts.subList(0,nameParts.size - 1).joinToString(separator = " ") else nameParts[0]
        val lastName = if(nameParts.size>1) nameParts[nameParts.size - 1] else ""

        val fName = remember { mutableStateOf(firstName) }
        val lName = remember { mutableStateOf(lastName) }
        val email = remember {
            vm.profileUi.value.data?.email?.let {
                mutableStateOf(it)
            } ?: mutableStateOf("")
        }
        val password = remember {
            state. value.data?.password?.let {
                mutableStateOf(it)
            } ?: mutableStateOf("")
        }
        val address = remember {
            state. value.data?.address?.let {
                mutableStateOf(it)
            } ?: mutableStateOf("")
        }
        val isEnable = remember {
            mutableStateOf(false)
        }
        val isAnimate = remember {
            mutableStateOf(false)
        }
        val btnText = if (isEnable.value) "Save" else "Edit Profile"
        val focusManager = LocalFocusManager.current
        Box(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background)) {
            Box(
                modifier = Modifier

                    .offset(x = (x).dp, y = (-200).dp)
                    .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                    .size(size = 350.dp)
                    .clip(
                        shape = CircleShape
                    )
            )
            Box(
                modifier = Modifier

                    .offset(x = (-160).dp, y = (y).dp)
                    .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                    .size(size = 250.dp)
                    .clip(
                        shape = CircleShape
                    )
            )

            Column(
                Modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent)
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

                ShoppingTextField2(
                    label = "Email",
                    value = email.value,
                    isEnable = isEnable.value
                ) {
                    email.value = it
                }
                if(uploadState.error.isNotEmpty()){
                    CustomAlertDialogue(message = uploadState.error.toString(), onDismiss = {
                        vm.updateState()
                    }) {
                        vm.updateState()
                    }
                }
                else if(uploadState.isLoaded){
                    CustomAlertDialogue(message = "Updated User Data", onDismiss = {
                        vm.updateState()
                    }) {
                        vm.updateState()
                    }
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
                    value = address.value
                ) {
                    address.value = it
                }


                if (isAnimate.value) {
                    ShoppingAlertDialog(onCancel = { isAnimate.value = !isAnimate.value }) {
                        auth.signOut().let {
                            isAnimate.value = !isAnimate.value
                            nav.navigate(Login)
                        }

                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                ShoppingButton(text = "Logout", containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onBackground) {
                    // nav.navigate(NavDestinations.LogOut)
                    isAnimate.value = !isAnimate.value
                }

                Spacer(modifier = Modifier.height(24.dp))
                ShoppingButton(text = btnText, containerColor = Color(0xFFD9D9D9)) {
                    focusManager.clearFocus()
                    if(btnText=="Save"){
                        vm.updateUserdata(
                            UserData(
                            name = "${fName.value} ${lName.value}",
                            email = email.value,
                            address = address.value,
                            password = password.value
                        )
                        )
                    }
                    isEnable.value = !isEnable.value
                }

            }

        }
    }
}


@Composable
fun ShoppingAlertDialog(
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {

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


@Composable
fun LoadingProfile(modifier: Modifier = Modifier) {
    val x = LocalConfiguration.current.screenWidthDp - 150
    val y = LocalConfiguration.current.screenHeightDp - 80
    Box(modifier.fillMaxSize()) {
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


            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = CircleShape)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                        .clip(shape = RoundedCornerShape(15.dp))
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                        .clip(shape = RoundedCornerShape(15.dp))
                        .shimmerEffect()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.height(20.dp))
            ShoppingButton(text = "Logout", containerColor = Color(0xFFF68B8B)) {
            }
            Spacer(modifier = Modifier.height(24.dp))
            ShoppingButton(text = "Edit", containerColor = Color(0xFFD9D9D9)) {

            }


        }
    }
}