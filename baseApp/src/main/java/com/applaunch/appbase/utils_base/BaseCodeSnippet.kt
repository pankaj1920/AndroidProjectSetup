package com.applaunch.appbase.utils_base

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.provider.Settings

class BaseCodeSnippet(val context: Context) {


    fun showNetworkSettings() {
        val chooserIntent = Intent.createChooser(
            getSettingsIntent(Settings.ACTION_DATA_ROAMING_SETTINGS),
            "Complete action using"
        )
        val networkIntents = ArrayList<Intent>()
        networkIntents.add(getSettingsIntent(Settings.ACTION_WIFI_SETTINGS))
        chooserIntent.putExtra(
            Intent.EXTRA_INITIAL_INTENTS,
            networkIntents.toTypedArray<Parcelable>()
        )
        chooserIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivityBySettings(context, chooserIntent)
    }

    private fun getSettingsIntent(settings: String): Intent {
        return Intent(settings)
    }

    private fun startActivityBySettings(context: Context, intent: Intent) {
        context.startActivity(intent)
    }


}