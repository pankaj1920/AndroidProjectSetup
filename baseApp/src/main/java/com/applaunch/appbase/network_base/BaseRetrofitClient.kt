package com.applaunch.appbase.network_base

import com.applaunch.appbase.BuildConfig
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.utils_base.BaseConstants
import com.applaunch.appbase.utils_base.Print
import com.applaunch.appbase.utils_base.session.currentLanguage
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseRetrofitClient(var iRepositoryListener: BaseRepoListener?) {

    protected fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    //Create OkHttpClient
    protected fun provideOkHttpClient(): OkHttpClient {
        Print.log("apiKey ${iRepositoryListener!!.getApiKey()}")
        Print.log("apiAppVersion ${iRepositoryListener!!.getAppVersion()}")
        Print.log("apiBearerToken ${iRepositoryListener!!.getBearerToken()}")

        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClientBuilder = OkHttpClient.Builder()

        okHttpClientBuilder.apply {
            connectTimeout(40, TimeUnit.SECONDS)
            readTimeout(40, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG)
                addInterceptor(loggingInterceptor)
        }
        okHttpClientBuilder.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header(
                    BaseConstants.Header.API_KEY, iRepositoryListener!!.getApiKey())
                .header(BaseConstants.Header.APP_VERSION, iRepositoryListener!!.getAppVersion())
                .header(BaseConstants.Header.AUTHORIZATION, "Bearer ${iRepositoryListener!!.getBearerToken()}")
                .header(BaseConstants.Header.LANGUAGE,currentLanguage)
                .header(BaseConstants.Header.DEVICE_TYPE,"android")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }
        return okHttpClientBuilder.build()
    }
}