package com.protecc.navigation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.protecc.AnimatedSplashScreen
import com.protecc.navigation.Screen.*


@Composable
fun SetupNavGraph (navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Splash.route
    ) {
        composable(route = Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Home.route) {
            Box(modifier = Modifier.fillMaxSize())
        }
        composable(route = Enter_pin.route) {
            Box(modifier = Modifier.fillMaxSize())
        }
    }
}