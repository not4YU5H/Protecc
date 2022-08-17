package com.protecc

import android.net.Uri
import android.os.Environment
import android.content.*
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.protecc.Utils.MyEncrypter
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun GalleryPicker() {
    var selectedImage by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
        selectedImage = uri

    }
    PickerContent(selectedImage) {
        launcher.launch("image/*")
    }

}

@Composable
fun Image_Enc (selectedImage: Uri? = null) {
    val context = LocalContext.current
    val date = Date()
    val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val FILE_NAME_ENC = "EncryptedImage ${dateFormat.format(date)}.jpg"

    val key="poOkWuKk9Be1Hq01" //16 char = 128 bit random unique key
    val specString = "H3yT5Cx1FDw1Uci0" //16 char = 128 bit
//    val root = Environment.getExternalStorageDirectory().let {
//        File(it,"Protecc").apply { mkdirs() }
//    }//.getRootDirectory().toString()
    val root = "/storage/emulated/0/Android/data/com.example.sampleimgencrypapp/files/system/"
    var myDir = File("$root/saved_images")
    if(!myDir.exists())
        myDir.mkdirs()



    val inputStream =  context.contentResolver.openInputStream(selectedImage!!)
    val drawable = Drawable.createFromStream(inputStream, selectedImage.toString())

    val bitmapDrawable = drawable as BitmapDrawable
    val bitmap = bitmapDrawable.bitmap
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
    val input= ByteArrayInputStream(stream.toByteArray())
    val outputFileEnc = File(myDir, FILE_NAME_ENC) // Create empty file enc


    try {
        MyEncrypter.encryptToFile(key, specString, input, FileOutputStream(outputFileEnc))
        Toast.makeText(context, "Encrypted", Toast.LENGTH_SHORT).show()
    }
    catch (e:Exception)
    {
        e.printStackTrace()
    }
}

@Composable
fun PickerContent (
    selectedImage: Uri? = null,
    onImageClick: () -> Unit
) {
    Scaffold() {
        Column (
            Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(selectedImage != null) {
                Image(painter = rememberAsyncImagePainter(selectedImage),
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onImageClick()
                        })
                Image_Enc(selectedImage)
            }
            else
                OutlinedButton(onClick = onImageClick) {
                    Text(text = "Choose Image")
                }
        }
    }
}



@Preview
@Composable
private fun Preview() {

}