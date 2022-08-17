package com.protecc

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.protecc.navigation.Screen
import com.protecc.ui.theme.color1
import com.protecc.ui.theme.color2

@Composable
fun File_type_screen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Gradient_button(
                text = "Images",
                textColor = Color.White,
                gradient = Brush.horizontalGradient(
                    colors = listOf(
                        color1,
                        color2
                    )
                )
            ) {
                navController.navigate(Screen.Images_screen.route)
            }
            Spacer(modifier = Modifier
                .padding(20.dp))
            Gradient_button(
                text = "  Notes  ",
                textColor = Color.White,
                gradient = Brush.horizontalGradient(
                    colors = listOf(
                        color1,
                        color2
                    )
                )
            ) { }

        }
        Row(

            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Gradient_button(
                text = "   Pdfs   ",
                textColor = Color.White,
                gradient = Brush.horizontalGradient(
                    colors = listOf(
                        color1,
                        color2
                    )
                )
            ) { }
            Spacer(modifier = Modifier
                .padding(20.dp))
            Gradient_button(
                text = " <Extra> ",
                textColor = Color.White,
                gradient = Brush.horizontalGradient(
                    colors = listOf(
                        color1,
                        color2
                    )
                )
            ) { }

        }
    }
}