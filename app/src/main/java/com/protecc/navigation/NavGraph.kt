@file:OptIn(ExperimentalMaterialApi::class)

package com.protecc.navigation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.protecc.*
import com.protecc.navigation.Screen.*

@ExperimentalMaterialApi
@Composable
fun SetupNavGraph (navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Splash.route
    ) {
        composable(route = Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Set_pin.route) {
            Set_pin(navController = navController)
        }
        composable(route = Enter_pin.route) {
            Enter_pin(navController = navController)
        }
        composable(route = GalleryPicker.route) {
            GalleryPicker()
        }
        composable(route = Home.route) {
            Box(modifier = Modifier.fillMaxSize())
        }
        composable(route = New_old_file.route) {

        }
        composable(route = File_type_screen.route) {
            File_type_screen(navController = navController)
        }
        composable(route = Images_screen.route) {
            Images_screen(navController = navController)
        }
    }
}