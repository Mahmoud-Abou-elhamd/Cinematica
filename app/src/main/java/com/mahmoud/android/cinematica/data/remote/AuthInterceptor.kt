package com.mahmoud.android.cinematica.data.remote

import com.mahmoud.android.cinematica.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    private val apiKey = BuildConfig.API_KEY
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val httpUrl = request.url.newBuilder().addQueryParameter(API_KEY_PARAMETER, apiKey).build()
        request = request.newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }

    companion object {
        private const val API_KEY_PARAMETER = "api_key"
    }
}