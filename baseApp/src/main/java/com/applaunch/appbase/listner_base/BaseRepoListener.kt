package com.applaunch.appbase.listner_base

interface BaseRepoListener {
    fun getBearerToken():String
    fun getApiKey():String
    fun getAppVersion():String
    fun showLoader()
    fun hideLoader()
    fun shimmer(isLoading:Boolean,shimmerType:String)
    fun showMessage(message: String)
    fun isNetworkConnected(): Boolean
    fun showErrorMessage(message: String)
}