package com.protecc.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object Enter_pin : Screen("enter_pin")
}