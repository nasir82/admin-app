package com.pks.shoppingappadmin.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Outbox
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class Items(
    val icon:ImageVector,
    val title:String
)

val navList = arrayOf(
    Items(icon = Icons.Default.Dashboard, title = "Dashboard"),
    Items(icon = Icons.Default.Outbox, title = "Order"),
    Items(icon = Icons.Default.Add, title = "AddProduct"),
    Items(icon = Icons.Default.Notifications, title = "Notification"),
    Items(icon = Icons.Default.Person, title = "Profile")

)