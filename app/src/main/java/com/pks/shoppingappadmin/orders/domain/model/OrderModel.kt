package com.pks.shoppingappadmin.orders.domain.model


import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Parcelize
@Serializable
data class OrderModel(
    val id: String?  = "",
    val receipt: String? = "",
    val price: Int? = 0,
    var status: String? = "",
    val approximateDelivery: String? = "",
    var preparedBy: String? = "",
    val location: String? = "",
    val deliveredBy:String?=""
):Parcelable

enum class OrderStatus{
    WAITING,
    CONFIRM,
    CAR,
    READY,
    REJECT
}

fun getStatus(status:String):String{
   return when(OrderStatus.valueOf(status)){
       OrderStatus.WAITING -> "Order is waiting"
       OrderStatus.CONFIRM -> "Order is confirmed"
       OrderStatus.CAR -> "Order is in the car"
       OrderStatus.READY -> "Order is ready"
       OrderStatus.REJECT -> "Order is rejected"
    }
}


val orderList = listOf(
    OrderModel(id = "1", receipt = "R1001", price = 100, status = OrderStatus.WAITING.name, approximateDelivery = "2024-09-20", preparedBy = "John", location = "Location A", deliveredBy = "DHL"),
    OrderModel(id = "2", receipt = "R1002", price = 200, status = OrderStatus.CONFIRM.name, approximateDelivery = "2024-09-21", preparedBy = "Alice", location = "Location B", deliveredBy = "FedEx"),
    OrderModel(id = "3", receipt = "R1003", price = 150, status = OrderStatus.WAITING.name, approximateDelivery = "2024-09-22", preparedBy = "Bob", location = "Location C", deliveredBy = "UPS"),
    OrderModel(id = "4", receipt = "R1004", price = 250, status = OrderStatus.CAR.name, approximateDelivery = "2024-09-23", preparedBy = "Carol", location = "Location D", deliveredBy = "DHL"),
    OrderModel(id = "5", receipt = "R1005", price = 300, status = OrderStatus.READY.name, approximateDelivery = "2024-09-24", preparedBy = "David", location = "Location E", deliveredBy = "FedEx"),
    OrderModel(id = "6", receipt = "R1006", price = 180, status = OrderStatus.WAITING.name, approximateDelivery = "2024-09-25", preparedBy = "Eve", location = "Location F", deliveredBy = "UPS"),
    OrderModel(id = "7", receipt = "R1007", price = 220, status = OrderStatus.READY.name, approximateDelivery = "2024-09-26", preparedBy = "Frank", location = "Location G", deliveredBy = "DHL"),
    OrderModel(id = "8", receipt = "R1008", price = 170, status = OrderStatus.READY.name, approximateDelivery = "2024-09-27", preparedBy = "Grace", location = "Location H", deliveredBy = "FedEx"),
    OrderModel(id = "9", receipt = "R1009", price = 210, status = OrderStatus.CONFIRM.name, approximateDelivery = "2024-09-28", preparedBy = "Hank", location = "Location I", deliveredBy = "UPS"),
    OrderModel(id = "10", receipt = "R1010", price = 260, status = OrderStatus.CAR.name, approximateDelivery = "2024-09-29", preparedBy = "Ivy", location = "Location J", deliveredBy = "DHL")
)

@OptIn(DelicateCoroutinesApi::class)
fun uploadDummyOrder(){

}


val OrderType = object:NavType<OrderModel>(false){
    override fun get(bundle: Bundle, key: String): OrderModel? {
        return if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            bundle.getParcelable(key,OrderModel::class.java)
        }else{
                bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): OrderModel {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: OrderModel) {
        bundle.putParcelable(key,value)
    }

    override fun serializeAsValue(value: OrderModel): String {
        return  Json.encodeToString(value)
    }

}