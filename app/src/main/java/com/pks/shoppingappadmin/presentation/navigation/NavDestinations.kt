package com.pks.shoppingappadmin.presentation.navigation

import com.pks.shoppingappadmin.orders.domain.model.OrderModel
import kotlinx.serialization.Serializable

@Serializable
object DashBoard
@Serializable
object AddProducts
@Serializable
object Notificaion
@Serializable
object Category
@Serializable
object Order
@Serializable
object Login
@Serializable
object SignUp
@Serializable
object LoginSignUp
@Serializable
object MainHome
@Serializable
object MainApp

@Serializable
object AddCategory
@Serializable
data class OrderDetails( val order:OrderModel)

@Serializable
data class AllProducts(val category:String)

