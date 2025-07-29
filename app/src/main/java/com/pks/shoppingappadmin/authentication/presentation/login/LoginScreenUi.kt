package com.pks.shoppingappadmin.authentication.presentation.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingappadmin.R
import com.pks.shoppingappadmin.authentication.presentation.AuthenticationViewModel
import com.pks.shoppingappadmin.category.presentation.AddCategoryViewModel
import com.pks.shoppingappadmin.components.DividerWithText
import com.pks.shoppingappadmin.components.LoginWithSocialMedia
import com.pks.shoppingappadmin.components.ShoppingButton
import com.pks.shoppingappadmin.components.ShoppingTextField
import com.pks.shoppingappadmin.navigation.App
import com.pks.shoppingappadmin.navigation.SignUp


@Composable
fun LoginScreenUi(
    viewModel: AuthenticationViewModel,
    nav: NavHostController,
    firebaseAuth: FirebaseAuth,
    addCategoryViewModel: AddCategoryViewModel
) {
    val x = LocalConfiguration.current.screenWidthDp - 150
    val y = LocalConfiguration.current.screenHeightDp - 80

    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }

    val isShow = remember {
        mutableStateOf(true)
    }

    val loginState = viewModel.loginScreenState.collectAsStateWithLifecycle()
    if (loginState.value.isLoading) {
        Box(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (loginState.value.error != null) {
        Box(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center) {
            Text(text = loginState.value.error.toString())
        }
    } else if (loginState.value.userData != null) {
        App(firebaseAuth = firebaseAuth, addCategoryViewModel = addCategoryViewModel)
    } else {

        var icon = if (isShow.value) Icons.Default.VisibilityOff else Icons.Default.Visibility
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

                Text(text = "Login", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(20.dp))


                ShoppingTextField(label = "Email", value = email.value) {
                    email.value = it
                }
                ShoppingTextField(
                    label = "password",
                    value = password.value,
                    visualTransformation = isShow.value,
                    trailingIcon = icon,
                    onTrailingClick = {
                        isShow.value = !isShow.value
                    }) {
                    password.value = it
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text(text = "Forgot Password?", modifier = Modifier.clickable { }, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onBackground)
                }

                Spacer(modifier = Modifier.height(20.dp))
                ShoppingButton(text = "Login", containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onBackground) {
                    if (email.value.isBlank() || password.value.isBlank()) {
                        Log.d("error", "not login")
                    } else {
                        viewModel.signIn(email = email.value, password = password.value)
                    }

                }

                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Don't have an account?  ", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
                    Text(
                        text = "Signup",
                         style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.clickable {
                            nav.navigate(SignUp)
                        })
                }

                Spacer(modifier = Modifier.height(24.dp))
                DividerWithText(text = "Or login with")
                Spacer(modifier = Modifier.height(24.dp))
                LoginWithSocialMedia(media = R.drawable.google, text = "Login with google")
                Spacer(modifier = Modifier.height(24.dp))
                LoginWithSocialMedia(media = R.drawable.facebook, text = "Login with facebook")

            }

        }
    }
}