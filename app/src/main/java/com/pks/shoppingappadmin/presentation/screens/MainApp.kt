package com.pks.shoppingappadmin.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingappadmin.presentation.navigation.navList


@Composable
fun MainApp(modifier: Modifier = Modifier,navHostController: NavHostController,auth: FirebaseAuth) {
    Log.d("mainApp","mainAPP e aiso ba")
    val selectedScreen = rememberSaveable {
        mutableIntStateOf(0)
    }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Scaffold(
            bottomBar = {
                BottomAppBar(

                    contentPadding = BottomAppBarDefaults.ContentPadding.apply {
                        PaddingValues(0.dp)
                    },

                    modifier = Modifier.height(90.dp)
                ) {

                    navList.forEachIndexed { index, navigationItem ->

                        NavigationBarItem(
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFFF68B8B)
                            ),
                            selected = selectedScreen.intValue == index,
                            onClick = {
                                selectedScreen.intValue = index

                            },
                            icon = {
                                Icon(imageVector = navigationItem.icon, contentDescription = "")
                            })
                    }

                }
            }
        ) {
            innerPad->
            innerPad
            when(selectedScreen.intValue){
                0 -> DashBoardScreenUi()
                1 -> OrderScreenUi(navHostController = navHostController)
                2 -> AddScreen(navHostController= navHostController)
                3 -> NotificationScreenUi()
                4 -> ProfileScreeUI(nav =navHostController , auth = auth)
            }

        }
    }
}