package com.example.mvvm.data.network

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import com.example.mvvm.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context:Context) : Interceptor {

    private val applicationContext=context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isInternetAvaliable())
            throw NoInternetException("Please check your internet connection")
        return chain.proceed(chain.request())

    }

    private fun isInternetAvaliable():Boolean{

        val connectivityManager =applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected

        }
    }
}