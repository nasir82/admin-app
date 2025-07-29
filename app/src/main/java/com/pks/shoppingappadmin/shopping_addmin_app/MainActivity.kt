package com.pks.shoppingappadmin.shopping_addmin_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseAuth
import com.pks.shoppingappadmin.category.presentation.AddCategoryViewModel
import com.pks.shoppingappadmin.navigation.App
import com.pks.shoppingappadmin.ui.theme.ShoppingAppAdminTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val vmd:AddCategoryViewModel = hiltViewModel()
            ShoppingAppAdminTheme {
                App(firebaseAuth = firebaseAuth, addCategoryViewModel = vmd)
            }
        }
    }
}
