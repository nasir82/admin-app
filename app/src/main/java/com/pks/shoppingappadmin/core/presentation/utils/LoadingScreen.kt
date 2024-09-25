package com.pks.shoppingappadmin.core.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pks.shoppingappadmin.R

@Composable
fun LoadingScreen(modifier: Modifier = Modifier,imageUrl:String="",text:String) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            

            //Gif
            Image(painterResource(id = R.drawable.img), contentDescription ="", modifier = Modifier.padding(horizontal = 30.dp) )
            
            Spacer(modifier = Modifier.heightIn(32.dp))
            
            Text(text = text)

            // text


        }
    }
}