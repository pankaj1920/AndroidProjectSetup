package com.applaunch.appbase.utils_base

interface BaseConstants {


    interface SharedKey {
        companion object {
            const val DATA_STORE_NAME = "bgm_session_manager"
            const val USER_DATA = "userData"
            const val TOKEN = "token"
            const val FCM_TOKEN = "fcmToken"
            const val ON_BOARDING_VIEWED = "onBoardingViewed"
            const val IS_CUSTOMER = "isCustomer"
        }
    }

    interface BaseKeys {
        companion object {
            const val SHOW_LOADER = "showLoader"
            const val SHOW_LOADER_MESSAGE = "showLoaderMessage"
            const val HIDE_LOADER = "hideLoader"
            const val SHOW_MESSAGE = "showMessage"
            const val APP = "com.payments.b2b"
        }
    }

    interface Header {
        companion object {
            const val AUTHORIZATION = "Authorization"
            const val API_KEY = "x-api-key"
            const val APP_VERSION = "x-app-version"
            const val LANGUAGE = "Accept-Language"
            const val DEVICE_TYPE = "x-app-deviceType"
        }
    }

    interface ErrorCode {
        companion object {
            const val BAD_REQUEST = 400
            const val UNAUTHORIZED = 401
            const val FORBIDDEN = 403
            const val NOT_FOUND = 404
            const val INTERNAL_SERVER_ERROR = 500
            const val SERVICE_UNAVAILABLE = 503
            const val GATEWAY_TIMEOUT = 504

        }
    }

    interface SuccessCode {
        companion object {
            const val SUCCESS = 200
            const val ACCEPTED = 202
            const val CREATED = 201
        }
    }

    interface SuccessStatus {
        companion object {
            const val TRUE = "TRUE"
            const val SUCCESS = "SUCCESS"
            const val TXN = "TXN"
            const val TXNOTP = "TXNOTP"
            const val TUP = "TUP"
            const val Pending = "Pending"
        }
    }


    interface HttpErrorMessage {
        companion object {

            const val NO_INTERNET = "No internet connection"
            const val INTERNAL_SERVER_ERROR =
                "Our server is under maintenance. We will resolve shortly!"
        }

    }

    interface AuthKey {
        companion object {
            const val AUTH_KEY =
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6Miwicm9sZUlkIjozLCJpYXQiOjE2MjgwNTcyMjd9.cdGGIUyWECI_Dq16cbJrHjEJ7wh5GPwktFBgHtbs-6E"
        }
    }

    interface Position{
        companion object{
            const val START = "start"
            const val  END = "end"
            const val TOP = "top"
            const val BOTTOM = "bottom"
        }
    }

    interface SHIMMER{
        companion object{
            const val NONE="none"
            const val ALL="all"
            const val PROFILE ="profile"
            const val RELAX ="relax"
            const val FAVORITE ="fav"
        }
    }
    object ColorPattern {
        const val ColorCodePattern = "[A-Z]{3}-?[0-9]{4}-?[A-Z]{3}"
    }

    interface TYPE{
        companion object{
            const val ARTICLE="Article"
            const val WEBINAR="Webinar"
        }
    }
}

