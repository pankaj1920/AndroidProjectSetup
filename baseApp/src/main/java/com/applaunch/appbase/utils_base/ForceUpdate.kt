package com.applaunch.appbase.utils_base

import android.app.Activity
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.tasks.Task

object AppUpdate {
    fun checkForUpdates(activity: Activity) {
        val appUpdateManager: AppUpdateManager = AppUpdateManagerFactory.create(activity)
        val appUpdateInfo: Task<AppUpdateInfo> = appUpdateManager.appUpdateInfo
        appUpdateInfo.addOnSuccessListener { _ ->
            handleImmediateUpdate(
                appUpdateManager,
                appUpdateInfo, activity
            )
        }
    }


    private fun handleImmediateUpdate(
        manager: AppUpdateManager,
        info: Task<AppUpdateInfo>,
        activity: Activity,
    ) {
        try {
            if ((info.result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE ||
                        info.result.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) &&
                info.result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                manager.startUpdateFlowForResult(
                    info.result,
                    AppUpdateType.IMMEDIATE,
                    activity,
                    100
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}