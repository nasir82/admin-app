package com.pks.shoppingappadmin.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout


@Preview(showBackground = true)
@Composable
fun ShoppingAppSnackBar(
    modifier: Modifier = Modifier, state: SnackbarHostState = remember {
        SnackbarHostState()
    },
    message: String = " ",
    code: Int = 0,
    onDismiss:()->Unit = {}
) {

    val color: Color = when (code) {
        0 -> Color(0xFFe23636)
        1 -> Color(0xFFedb95e)
        else -> Color(0xFF82dd55)
    }
    val icon: ImageVector = when (code) {
        0 -> Icons.Default.Error
        1 -> Icons.Default.Warning
        else -> Icons.Default.Done
    }

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val snackBar = createRef()

        SnackbarHost(

            modifier = modifier
                .padding(
                    start = 16.dp, end = 16.dp, bottom = 32.dp
                )
                .constrainAs(
                    snackBar
                ) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },

            hostState = state,
            snackbar = {
                Snackbar(
                    containerColor = color,
                    contentColor = Color.Black,
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.heightIn(min = 100.dp),
                    dismissAction = {
                        onDismiss.invoke()
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = icon,
                            contentDescription = "",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = message, modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(5.dp))
                        IconButton(onClick = {
                            state.currentSnackbarData?.dismiss()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(shape = CircleShape)
                            )
                        }
                    }

                }
            }
        )
    }


}


