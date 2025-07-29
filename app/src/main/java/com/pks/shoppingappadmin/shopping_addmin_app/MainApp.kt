package com.pks.shoppingappadmin.shopping_addmin_app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingappadmin.authentication.presentation.AuthenticationViewModel
import com.pks.shoppingappadmin.authentication.presentation.profile.ProfileScreeUI
import com.pks.shoppingappadmin.category.presentation.AddCategoryViewModel
import com.pks.shoppingappadmin.category.presentation.show_category.DashBoardScreenUi
import com.pks.shoppingappadmin.navigation.navList
import com.pks.shoppingappadmin.notification.presentation.NotificationScreenUi
import com.pks.shoppingappadmin.orders.presentation.OrderScreenUi
import com.pks.shoppingappadmin.orders.presentation.OrderViewModel
import com.pks.shoppingappadmin.core.presentation.AddScreen


@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    auth: FirebaseAuth,
    orderViewModel: OrderViewModel,
     viewmodel: AuthenticationViewModel,
    addCategoryViewModel: AddCategoryViewModel
) {

    if(auth.currentUser != null) viewmodel.getUserByUid(auth.currentUser!!.uid)
    val profileState = viewmodel.profileScreenState.collectAsState()
    val selectedScreen = rememberSaveable {
        mutableIntStateOf(0)
    }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
                                Icon(navigationItem.icon, contentDescription = "", modifier = Modifier.size(28.dp))
                            })
                    }

                }
            }
        ) { innerPad ->
            innerPad
            when (selectedScreen.intValue) {
                0 -> DashBoardScreenUi(nav = navHostController, viewModel = addCategoryViewModel)
                1 -> OrderScreenUi(
                    navHostController = navHostController,
                    viewModel = orderViewModel
                )

                2 -> AddScreen(navHostController = navHostController,viewModel = addCategoryViewModel)
                3 -> NotificationScreenUi()
                4 -> ProfileScreeUI(
                    auth = auth,
                    nav = navHostController,
                    state = profileState,
                    vm = viewmodel
                )

                //ProfileScreeUI(nav =navHostController , auth = auth)
            }

        }
    }
}

