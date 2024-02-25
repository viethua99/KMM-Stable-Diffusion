package com.vproject.stablediffusion.util

import android.content.Context
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

actual fun imageBitmapFromBytes(encodedImageData: ByteArray): ImageBitmap {
    return BitmapFactory.decodeByteArray(encodedImageData, 0, encodedImageData.size).asImageBitmap()
}

actual class TestUtil(private val context: Context) {
    actual fun isInternetAvailable(): Boolean {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("https://api.stability.ai")
                val urlc =  url.openConnection() as HttpURLConnection;
                urlc.connectTimeout = 4000
                urlc.connect();
                if (urlc.responseCode == 200) {
                    Log.d("HELLO", "Success !")
                } else {
                    Log.d("HELLO", "Failed !")
                }
            } catch (e1: MalformedURLException) {
                Log.d("HELLO", "MalformedURLException !")

                e1.printStackTrace()
            } catch (e: IOException) {
                Log.d("HELLO", "IOException !")
                e.printStackTrace()
            }
        }

        /////
        var result = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return result
    }
}