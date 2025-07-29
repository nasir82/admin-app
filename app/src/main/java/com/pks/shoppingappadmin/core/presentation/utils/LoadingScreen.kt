package com.pks.shoppingappadmin.core.presentation.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.pks.shoppingappadmin.R

@Composable
fun LoadingScreen(modifier: Modifier = Modifier,imageUrl:String="",text:String) {
    val animation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))
    Box(modifier = modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            //Gif
           // Image(painterResource(id = R.drawable.img), contentDescription ="", modifier = Modifier.padding(horizontal = 30.dp) )
            LottieAnimation(composition = animation,modifier = Modifier.size(280.dp))
            Spacer(modifier = Modifier.heightIn(32.dp))
            Text(text = text, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)

        }
    }
}