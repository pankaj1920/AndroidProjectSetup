package com.applaunch.appbase.utils_base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkConnection(val mContext: Context) {

    fun hasNetworkConnection(): Boolean {
        val connectivityManager =
            mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        var isConnected = false


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /*get the active network connected to the device */
            val networkCapabilities = connectivityManager.activeNetwork ?: return false

            /*  checks for the strong bandwidth */
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            isConnected = when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                else -> false
            }
        } else {
            // Retrieve current status of connectivity
            connectivityManager.allNetworks.forEach { network ->
                val networkCapability = connectivityManager.getNetworkCapabilities(network)

                networkCapability?.let {
                    if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                        isConnected = true
                        return@forEach
                    }
                }
            }
        }

        /*  checks for the strong bandwidth */

        return isConnected
    }
}