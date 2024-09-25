package com.pks.shoppingappadmin.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingappadmin.addproduct.presentation.AddProductsScreenUi
import com.pks.shoppingappadmin.allproduct.AllProductScreen
import com.pks.shoppingappadmin.authentication.presentation.AuthenticationViewModel
import com.pks.shoppingappadmin.authentication.presentation.login.LoginScreenUi
import com.pks.shoppingappadmin.authentication.presentation.signup.SignUpScreenUI
import com.pks.shoppingappadmin.category.presentation.AddCategoryViewModel
import com.pks.shoppingappadmin.category.presentation.add_category.AddCategoryScreenUi
import com.pks.shoppingappadmin.orders.domain.model.OrderModel
import com.pks.shoppingappadmin.orders.domain.model.OrderType
import com.pks.shoppingappadmin.orders.presentation.OrderDetailsScreenUI
import com.pks.shoppingappadmin.orders.presentation.OrderViewModel
import com.pks.shoppingappadmin.showcategory.presentation.CategoryViewModel
import kotlin.reflect.typeOf

@Composable
fun App(
    modifier: Modifier = Modifier,
    firebaseAuth: FirebaseAuth,
    categoryViewModel: CategoryViewModel,
    addCategoryViewModel: AddCategoryViewModel
) {

    val navHostController = rememberNavController()
    val orderViewModel: OrderViewModel = hiltViewModel()
    val authenticationViewModel: AuthenticationViewModel = hiltViewModel()
    val startDestination = if (firebaseAuth.currentUser == null) {
        LoginSignUp
    } else {
        //load user data
        MainHome
    }
    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navHostController, startDestination = startDestination) {

            navigation<LoginSignUp>(
                startDestination = Login
            ) {
                composable<Login> {
                    LoginScreenUi(
                        nav = navHostController,
                        firebaseAuth = firebaseAuth,
                        viewModel = authenticationViewModel,
                        categoryViewModel = categoryViewModel,
                        addCategoryViewModel = addCategoryViewModel
                    )
                }
                composable<SignUp> {
                    SignUpScreenUI(
                        nav = navHostController,
                        auth = firebaseAuth,
                        viewModel = authenticationViewModel,
                        categoryViewModel = categoryViewModel,
                        addCategoryViewModel = addCategoryViewModel
                    )
                }
            }
            navigation<MainHome>(
                startDestination = MainApp
            ) {
                composable<DashBoard> { }
                composable<AddProducts> {
                    AddProductsScreenUi()
                }
                composable<AddCategory> {
                    AddCategoryScreenUi(
                        viewModel = addCategoryViewModel,
                        navController = navHostController
                    )
                }

                composable<Order> {
                    // OrderScreenUi()
                }
                composable<Notificaion> { }
                composable<AllProducts> {
                    val name = it.toRoute<AllProducts>().category
                    AllProductScreen(
                        nav = navHostController,
                        category = name,
                        viewModel = categoryViewModel
                    )
                }
                composable<Category> { }
                composable<OrderDetails>(
                    typeMap = mapOf(typeOf<OrderModel>() to OrderType)
                ) {

                    val order = it.toRoute<OrderDetails>().order
                    OrderDetailsScreenUI(
                        order = order,
                        navHostController = navHostController,
                        viewModel = orderViewModel
                    )
                }


                composable<MainApp> {
                    com.pks.shoppingappadmin.presentation.screens.MainApp(
                        navHostController = navHostController,
                        auth = firebaseAuth,
                        orderViewModel = orderViewModel,
                        categoryViewModel = categoryViewModel,
                        viewmodel = authenticationViewModel,
                        addCategoryViewModel = addCategoryViewModel
                    )
                }
            }


        }
    }
}

