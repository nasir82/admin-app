package com.pks.shoppingappadmin.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.rounded.Person3
import androidx.compose.ui.graphics.vector.ImageVector

data class Items(
    val icon:ImageVector,
    val title:String
)

val navList = arrayOf(
    Items(icon = Icons.Default.Dashboard, title = "Dashboard"),
    Items(icon = Icons.Default.Receipt, title = "Order"),
    Items(icon = Icons.Outlined.AddCircle, title = "AddProduct"),
    Items(icon = Icons.Default.NotificationsActive, title = "Notification"),
    Items(icon = Icons.Rounded.Person3, title = "Profile")

)