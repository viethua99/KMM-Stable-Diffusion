package com.vproject.stablediffusion

import App
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }

//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val url = URL("https://api.stability.ai/v1/generation/stable-diffusion-v1-6/text-to-image")
//                val urlc =  url.openConnection() as HttpURLConnection;
//                urlc.connectTimeout = 4000
//                urlc.connect();
//                if (urlc.responseCode == 200) {
//                    Log.d("HELLO", "Success !")
//                } else {
//                    Log.d("HELLO", "Failed !")
//                }
//            } catch (e1: MalformedURLException) {
//                Log.d("HELLO", "MalformedURLException !")
//
//                e1.printStackTrace()
//            } catch (e: IOException) {
//                Log.d("HELLO", "IOException !")
//                e.printStackTrace()
//            }
//        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}