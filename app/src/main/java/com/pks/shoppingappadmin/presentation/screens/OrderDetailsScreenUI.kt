package com.pks.shoppingappadmin.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pks.shoppingappadmin.components.ShoppingButton
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState


@Preview(showBackground = true)
@Composable
fun OrderDetailsScreenUI(modifier: Modifier = Modifier.fillMaxSize(),url:String="") {
    Column(modifier = modifier){

        Spacer(modifier = Modifier.height(40.dp))
     Box(modifier = Modifier.weight(1f)){

         val pdfState = rememberVerticalPdfReaderState(
             resource = ResourceType.Remote("https://firebasestorage.googleapis.com/v0/b/shoppingappadmin-b6509.appspot.com/o/pdf%2Fsample1%20(1).pdf?alt=media&token=b8d01b57-21a2-4fdc-b5f7-380bcc794c77"),
             isZoomEnable = true
         )



         VerticalPDFReader(
             state = pdfState, modifier = Modifier
                 .fillMaxSize()
                 .background(color = Color.Transparent)
         )

     }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.End) {
            ShoppingButton(text = "cancel", modifier = Modifier.wrapContentWidth()) {
                
            }
            Spacer(modifier = Modifier.width(20.dp))
            ShoppingButton(text = "Reject", modifier = Modifier.wrapContentWidth(), containerColor = Color.Red) {

            }
            Spacer(modifier = Modifier.width(20.dp))
            ShoppingButton(text = "Accept", modifier = Modifier.wrapContentWidth(), containerColor = Color.Green) {

            }
        }
        androidx.compose.foundation.layout.Spacer(modifier =Modifier. height(50.dp))
    }
}