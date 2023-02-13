package com.applaunch.appbase.utils_base

import android.content.Context
import android.view.Gravity
import android.widget.Toast

object Toaster {

    fun show(context: Context, text: String) {
        val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun show(context: Context, text: Int?) {
        val toast = Toast.makeText(context, text!!, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}

