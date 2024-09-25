package com.pks.shoppingappadmin.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.pks.shoppingappadmin.authentication.domain.usecase.CreateUserUseCase
import com.pks.shoppingappadmin.authentication.domain.usecase.GetUserUseCase
import com.pks.shoppingappadmin.authentication.presentation.profile.ProfileScreenState
import com.pks.shoppingappadmin.domain.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingAppViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    val db = FirebaseFirestore.getInstance()
    /// this is customizeable

    private val _profileScreenState = MutableStateFlow(ProfileScreenState())
    val profileScreenState = _profileScreenState.asStateFlow()
    //okay

    var lst:List<ProductModel>  = emptyList()
    fun uploadProduct(){
        var cnt = 0
        viewModelScope.launch {


      db.collection("Products").get().addOnSuccessListener {
            val instance =   it.map {obj ->
                 obj.toObject(ProductModel::class.java)
               }
          Log.d("collectedProd", instance.toString())
           }

        }
    }



}

//data class CategoryState(
//    val success: String? = "",
//    val isLoading: Boolean = false,
//    val error: String = ""
//)


data class SignupScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val userData: String? = null
)