package com.applaunch.appbase.utils_base

import com.applaunch.appbase.model_base.ErrorResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody


fun checkApiCode(apicode:Int):Boolean{
    return (apicode == BaseConstants.SuccessCode.SUCCESS
            || apicode == BaseConstants.SuccessCode.ACCEPTED || apicode == BaseConstants.SuccessCode.CREATED)
}


 fun convertErrorBody(errorBody: ResponseBody?): ErrorResponse? {
    var errorResponse: ErrorResponse?
    return try {
        errorBody?.source().let {
            val gson = Gson()
            val type = object : TypeToken<ErrorResponse>() {}.type
            errorResponse = gson.fromJson(errorBody?.charStream(), type)
        }
        errorResponse
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}