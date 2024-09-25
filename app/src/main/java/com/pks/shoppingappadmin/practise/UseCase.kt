package com.pks.shoppingappadmin.practise

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun getPasswordErrorText(error: UserDataValidator.PasswordError): String {
    return when (error) {
        UserDataValidator.PasswordError.TOO_SHORT -> "Password is too short"
        UserDataValidator.PasswordError.NO_UPPERCASE -> "Password must contain an uppercase letter"
        UserDataValidator.PasswordError.NO_DIGIT -> "Password must contain a digit"
        UserDataValidator.PasswordError.NO_SPECIAL_CHARACTER -> "Password must contain a special character"
    }
}


@Composable
fun UseCaseImp(modifier: Modifier = Modifier) {

    val check = remember {
        mutableStateOf("")
    }
    val validator = UserDataValidator()
    var passwordError by remember { mutableStateOf<UserDataValidator.PasswordError?>(null) }
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = check.value, onValueChange = {
                check.value = it;
                val result = validator.validatePassword(it)
                passwordError = if (result is Result.Error) result.error else null

            },
            isError = passwordError != null
        )
        passwordError?.let {
            Text(
                text = getPasswordErrorText(it),
                color = Color.Red,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}