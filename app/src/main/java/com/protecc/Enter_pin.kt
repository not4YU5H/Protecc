package com.protecc

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.datastore.dataStore
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.protecc.datastore.StoreUserPin
import com.protecc.navigation.Screen
import com.protecc.ui.theme.*
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
import com.google.android.exoplayer2.ui.StyledPlayerView

@ExperimentalMaterialApi
@Composable
fun Enter_pin (navController: NavHostController) {

    val context = LocalContext.current

    val packageName = "com.protecc"
    val rawId = context.resources.getIdentifier("clouds","raw", packageName)
    val videoUri = "android.resource://$packageName/$rawId"

    val dataStore = StoreUserPin(context)
    val savedPin = dataStore.getPin.collectAsState(initial = "")
    val exoPlayer = remember { context.buildExoPlayer(Uri.parse(videoUri)) }

    BackEffect(exoPlayer)

    Column(
        modifier = Modifier
            .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {


        var password by rememberSaveable { mutableStateOf("") }
        var passwordVisibility by remember { mutableStateOf(false) }
        var icon = if(passwordVisibility) painterResource(id = R.drawable.security_icons_eye) else painterResource(id = R.drawable.security_icons_incognito)
        val maxChar = 4
        var isError by remember { mutableStateOf(false) }
        OutlinedTextField(value = password, onValueChange = {
            if(it.length <= maxChar) {
                password = it
            }
            isError = it.length < 4
        },
        placeholder = { Text(text = "Enter PIN")},
            label = { Text(text = "4-digit PIN")},
            maxLines = 1,
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painter = icon, contentDescription = "Visibility Icon", tint = Color.Unspecified)
//                    Image(painter = icon, contentDescription = "Visibility Icon")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,

            ),
            visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            isError = isError

        )
        Spacer(modifier = Modifier.padding(20.dp))
//        AnimatedButton(
//             onClicked = {
////                if(isError)
////                {
////                    showMessage(context, message = "Error: PIN length should be 4!")
////                }
////                else {
////                    navController.navigate(Screen.GalleryPicker.route)
//                    navController.navigate(Screen.File_type_screen.route)
////                }
//    }
//
//        )
        
        Gradient_button(text = "Unlock!", textColor = Color.White, gradient = Brush.horizontalGradient(
            colors = listOf(color1, color2)
        )) {
            if(password == savedPin.value)
            {
                navController.popBackStack()
                navController.navigate(Screen.File_type_screen.route)
            }
            else
            {
                showMessage(context, message = "Wrong Pin")
            }
        }
    }
}

private fun Context.buildExoPlayer(uri: Uri) =
    ExoPlayer.Builder(this).build().apply {
        setMediaItem(MediaItem.fromUri(uri))
        repeatMode = Player.REPEAT_MODE_ALL
        playWhenReady = true
        prepare()
    }

private fun Context.buildPlayerView(exoPlayer: ExoPlayer) =
    StyledPlayerView(this).apply {
        player = exoPlayer
        layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        useController = false
        resizeMode = RESIZE_MODE_ZOOM
    }

@Composable
fun BackEffect(exoPlayer: ExoPlayer) {
    DisposableEffect(
        AndroidView(
            factory = { it.buildPlayerView(exoPlayer) },
            modifier = Modifier.fillMaxSize()
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
}

//fun getVideoUri(context: Context): Uri {
////    val rawId = resources.getIdentifier("clouds", "raw", packageName)
//    val packageName = "com.protecc"
//    val rawId = context.resources.getIdentifier("clouds","raw", packageName)
//    val videoUri = "android.resource://$packageName/$rawId"
//    return Uri.parse(videoUri)
//}


fun showMessage(context: Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

