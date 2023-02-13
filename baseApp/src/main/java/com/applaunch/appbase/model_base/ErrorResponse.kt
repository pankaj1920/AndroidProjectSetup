package com.applaunch.appbase.model_base

data class ErrorResponse(
    var statuscode : String?,
    var status : String?,
    var success : String?,
    var message :String?
)