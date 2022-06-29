package com.protecc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.protecc.navigation.Screen
import com.protecc.ui.theme.ProteccTheme
import com.protecc.ui.theme.Purple700

@Composable
fun Enter_pin () {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var password by rememberSaveable { mutableStateOf("") }
        var passwordVisibility by remember { mutableStateOf(false) }
        var icon = if(passwordVisibility) painterResource(id = R.drawable.security_icons_eye) else painterResource(id = R.drawable.security_icons_incognito)
        val maxChar = 4
        OutlinedTextField(value = password, onValueChange = {
            if(it.length <= maxChar) {
                password = it
            }
        },
        placeholder = { Text(text = "Enter PIN")},
            label = { Text(text = "4-digit PIN")},
            maxLines = 1,
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.security_icons_eye1), contentDescription = "Visibility Icon")
                    Image(painter = icon, contentDescription = "Visibility Icon")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,

            ),
            visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
        )
    }
}

