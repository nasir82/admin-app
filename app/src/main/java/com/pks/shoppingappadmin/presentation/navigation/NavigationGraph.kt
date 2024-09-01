package com.pks.shoppingappadmin.presentation.navigation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingappadmin.presentation.screens.AddCategoryScreenUi
import com.pks.shoppingappadmin.presentation.screens.AddProductsScreenUi
import com.pks.shoppingappadmin.presentation.screens.LoginScreenUi
import com.pks.shoppingappadmin.presentation.screens.OrderDetailsScreenUI
import com.pks.shoppingappadmin.presentation.screens.SignUpScreenUI

@Composable
fun App(modifier: Modifier = Modifier,firebaseAuth: FirebaseAuth) {

    val navHostController = rememberNavController()
    val startDestination =  if(firebaseAuth.currentUser == null) {
        Log.d("mainApp","login assing")
        LoginSignUp
    } else {
        Log.d("mainApp","main assing")
        MainHome
    }
    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navHostController, startDestination = startDestination ){

            navigation<LoginSignUp>(
                startDestination = Login
            ){
                composable<Login> {
                    LoginScreenUi(nav = navHostController, firebaseAuth =firebaseAuth )
                }
                composable<SignUp> {
                    SignUpScreenUI(nav = navHostController, auth = firebaseAuth)
                }
            }
            navigation<MainHome>(
                startDestination = MainApp
            ){
                composable<DashBoard> {  }
                composable<AddProducts> {
                    AddProductsScreenUi()
                }
                 composable<AddCategory> {
                    AddCategoryScreenUi()
                }

                composable<Order> {  }
                composable<Notificaion> {  }
                composable<Category> {  }
                composable<OrderDetails> {
                    OrderDetailsScreenUI()
                }

                
                composable<MainApp> {
                    com.pks.shoppingappadmin.presentation.screens.MainApp(navHostController = navHostController, auth = firebaseAuth)
                }
            }



        }
    }
}

