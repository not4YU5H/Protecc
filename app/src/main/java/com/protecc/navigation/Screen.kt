package com.protecc.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object Enter_pin : Screen("enter_pin")
    object GalleryPicker : Screen("gallery_picker_screen")
    object New_old_file : Screen("new_old_file")
    object File_type_screen : Screen("file_type_screen")
}