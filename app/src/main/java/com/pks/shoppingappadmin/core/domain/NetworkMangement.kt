package com.pks.shoppingappadmin.core.domain

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

sealed interface NetworkConnectionState{
    data object Available: NetworkConnectionState
    data object Unavailable: NetworkConnectionState
}


private  fun networkCallback(callback:(NetworkConnectionState)-> Unit): ConnectivityManager.NetworkCallback =
    object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            callback(NetworkConnectionState.Available)
        }

        override fun onLost(network: Network) {
            callback(NetworkConnectionState.Unavailable)
        }

        override fun onUnavailable() {
            callback(NetworkConnectionState.Unavailable)
        }
    }


fun getCurrentConnectivityState(connectivityManager: ConnectivityManager):NetworkConnectionState{
    val network = connectivityManager.activeNetwork
    val isConnected  = connectivityManager.getNetworkCapabilities(
        network
    )?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)?:false

    return if(isConnected) NetworkConnectionState.Available else NetworkConnectionState.Unavailable
}

fun Context.observeConnectivityAsFlow(): Flow<NetworkConnectionState> = callbackFlow {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val callback = networkCallback {
        connectionState->
        trySend(connectionState)
    }

    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    connectivityManager.registerNetworkCallback(networkRequest,callback)
    val currentState = getCurrentConnectivityState(connectivityManager)
    trySend(currentState)

    awaitClose{
        connectivityManager.unregisterNetworkCallback(callback)
    }

}

val  Context.currentConnectionState:NetworkConnectionState get() {
    val  connectivityManager =getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return  getCurrentConnectivityState(connectivityManager)
}


@Composable
fun rememberConnectivityState(): State<NetworkConnectionState>{
    val context = LocalContext.current
    return  produceState(initialValue = context.currentConnectionState) {
        context.observeConnectivityAsFlow().collect{
            value = it
        }

    }
}






// demo screen for use case of connection status

@Composable
fun NetworkStatusScreen() {
    // Remember the network connectivity state
    val connectionState = rememberConnectivityState()
    val context= LocalContext.current
    val text = remember {
        mutableStateOf("Do")
    }
    // UI based on the connection state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (connectionState.value) {
            is NetworkConnectionState.Available -> {
                Text(text = "You are connected to the Internet.", color = MaterialTheme.colorScheme.primary)
            }
            is NetworkConnectionState.Unavailable -> {
                Text(text = "No internet connection.", color = MaterialTheme.colorScheme.error)
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Trigger any method that depends on network status
//            performNetworkAction(connectionState.value){
//                text.value = "connected"
//            }
           if(isNetworkAvailable(context))   text.value = "hae"
            else text.value = "nae"
        }) {
            Text(text = text.value)
        }
    }
}

fun performNetworkAction(networkState: NetworkConnectionState,onConn:()-> Unit) {
    if (networkState is NetworkConnectionState.Available) {
        onConn.invoke()
        println("Network is available, proceeding with action.")
    } else {
        // Show message or handle lack of connection
        println("Network is unavailable, action cannot be performed.")
    }
}

@Preview(showBackground = true)
@Composable
fun NetworkStatusScreenPreview() {
    NetworkStatusScreen()
}


fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}