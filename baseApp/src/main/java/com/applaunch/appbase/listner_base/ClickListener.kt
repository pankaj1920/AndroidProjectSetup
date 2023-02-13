package com.applaunch.appbase.listner_base

var networkConnected: ((isConnected: Boolean) -> Unit)? = null

var unAuthorizeUser: ((message: String) -> Unit)? = null
var showShimmer: ((isLoading:Boolean,shimmerType:String) -> Unit)? = null