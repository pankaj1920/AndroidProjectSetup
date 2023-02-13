package com.applaunch.appbase.utils_base

import android.util.Log
import com.applaunch.appbase.BuildConfig


object Print {
    fun log(message: String?) {
        if (BuildConfig.DEBUG) {
            println("##################################")
            println("Print : $message")
            Log.e("Print", "Print : $message")

        }

    }
}