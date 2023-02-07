package com.rahim.taskmanager

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadIMage(image: String){
    Glide.with(this).load(image).into(this)
}

fun Context.isOnline(): Boolean {
   val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
   val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
   if (capabilities != null){
       if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
           return true
       } else if(capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
           return true
       }
       else if(capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
           return true
       }
   }
    return false
}