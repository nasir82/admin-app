package com.pks.shoppingappadmin.products.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pks.shoppingappadmin.common.ResultState
import com.pks.shoppingappadmin.products.domain.model.ProductModel
import com.pks.shoppingappadmin.products.domain.model.ProductVariationsModel
import com.pks.shoppingappadmin.products.domain.repo.AddProductRepo
import com.pks.shoppingappadmin.products.domain.use_case.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(val repo:AddProductRepo,val getProductsUseCase: GetProductsUseCase) :ViewModel() {

    val product = mutableStateOf(ProductModel())
    val sku = mutableStateOf("")

    private val _addProductScreenState = MutableStateFlow(AddProductScreenState())
    val addProductScreenState = _addProductScreenState.asStateFlow()

    private val _productState = MutableStateFlow(ProductState())
    val productState = _productState.asStateFlow()

    init {
        getProduct()
    }
    fun addProduct(productModel: ProductModel){
           _addProductScreenState.value = AddProductScreenState(isLoading = true)
        if(productModel.thumbnail.isEmpty()){
            _addProductScreenState.value = AddProductScreenState(error = "Please Select an thumbnail Image")
        }else if(productModel.images.isEmpty()){
            _addProductScreenState.value = AddProductScreenState(error = "Please Select an product Images")
        }else {

            viewModelScope.launch {
                delay(5000)
                val thumbUrl = repo.addImages(listOf(productModel.thumbnail))
                if(thumbUrl.isEmpty()){
                    _addProductScreenState.value = AddProductScreenState(error = "can't upload thumbnail image")
                    return@launch
                }else{
                    productModel.thumbnail = thumbUrl[0].toString()
                }
                val images = repo.addImages(productModel.images)
                if(images.isEmpty()){
                    _addProductScreenState.value = AddProductScreenState(error = "can't upload images")
                    return@launch
                }else{
                    productModel.images =images
                }
               if (productModel.brand.image.isEmpty()){
                   val logo = repo.addImages( listOf(productModel.brand.image))
                   if(logo.isEmpty()){
                       _addProductScreenState.value = AddProductScreenState(error = "can't upload images brand icon")
                       return@launch
                   }else{
                       productModel.brand.image =logo[0].toString()
                   }
               }

                if(productModel.productVariation.isNotEmpty()){
                    val variationlist = mutableListOf<ProductVariationsModel>()
                    productModel.productVariation.forEach {variation ->
                        if(variation.image.isNotEmpty()){
                            val varImage = repo.addImages( listOf(variation.image))
                            if(varImage.isEmpty()){
                                _addProductScreenState.value = AddProductScreenState(error = "can't upload variation images")
                                return@launch
                            }else{
                                variation.image = varImage[0]
                                variationlist.add(variation)
                            }
                        }

                    }
                    productModel.productVariation = variationlist
                }

                repo.addProduct(productModel).collectLatest {
                    when (it) {
                        is ResultState.Error -> {
                            _addProductScreenState.value = AddProductScreenState(error = it.message)
                        }

                        ResultState.Loading -> {
                            _addProductScreenState.value = AddProductScreenState(isLoading = true)
                        }

                        is ResultState.Success -> {
                            _addProductScreenState.value = AddProductScreenState(success = it.data)
                        }
                    }

                }
            }
        }
    }

    //fetch the products

    fun getProduct() {
        viewModelScope.launch {
            Log.d("gettign products...","start")
            getProductsUseCase.getProducts().collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        _productState.value = ProductState(error = it.message)
                    }

                    ResultState.Loading -> {
                        _productState.value = ProductState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        //statusList.value  = it.data.data ?: emptyList()
                        _productState.value = ProductState(data = it.data.data)

                        Log.d("gettign products...","end with ${it.data.data}")
                    }
                }
            }
            Log.d("gettign products...","end")
        }
    }
}

data class AddProductScreenState(
    var isLoading:Boolean = false,
    var success:String = "",
    var error:String = ""
)