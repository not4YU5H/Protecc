package com.protecc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.protecc.navigation.Screen

@Composable
fun Images_screen (navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    verticalArrangement = Arrangement.Bottom,
    horizontalAlignment = Alignment.End) {
        FloatingActionButton(onClick = { navController.navigate(Screen.GalleryPicker.route) },
        backgroundColor = Color.Blue,
        contentColor = Color.White
        ) {
            Icon(Icons.Filled.Add, "")
        }
    }
}