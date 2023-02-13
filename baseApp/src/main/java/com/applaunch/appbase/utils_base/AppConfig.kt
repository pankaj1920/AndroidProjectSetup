package com.applaunch.appbase.utils_base

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.URLUtil
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.applaunch.appbase.BuildConfig


fun Activity.changeStatusBarColor(colorId: Int) {
    window.statusBarColor = this.resources.color(colorId)
}

fun Activity.forceLightTheme() {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
}

fun AppCompatActivity.onBackPressed(isFinish: Boolean = true, onBackPressed: () -> Unit) {
    onBackPressedDispatcher.addCallback(this,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed.invoke()
                if (isFinish) finish()
            }
        }
    )
}

fun Fragment.onBackPressed(isFinish: Boolean = true, onBackPressed: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(this,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed.invoke()
                if (isFinish) requireActivity().finish()
            }
        }
    )
}

fun Context.shareLink(url: String) {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
        this.startActivity(intent)
}

fun Context.openWebPage( url: String?) {
    try {
        if (!URLUtil.isValidUrl(url)) {
            Toast.makeText(this, " This is not a valid link", Toast.LENGTH_LONG).show()
        } else {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            this.startActivity(intent)
        }
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, " You don't have any browser to open web page", Toast.LENGTH_LONG)
            .show()
    }
}

fun appLog(str:String){
    if(BuildConfig.DEBUG){
        Log.e("LOG", ""+str);
    }
}
