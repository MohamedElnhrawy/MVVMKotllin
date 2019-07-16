package com.example.mvvm.data.network

import com.example.mvvm.data.network.response.AuthResoinse
import com.example.mvvm.data.network.response.QuoteResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.*

interface MyApi {

    @FormUrlEncoded
    @POST("login")
     suspend fun Login(@Field("email") email:String ,
              @Field("password") password:String): Response<AuthResoinse>


    @FormUrlEncoded
    @POST("signup")
    suspend fun Signup(@Field("name") name : String ,
                       @Field("email") email:String,
                       @Field("password") password:String):Response<AuthResoinse>

    @GET("quotes")
    fun getQoutes():Response<QuoteResponse>

    companion object{
        operator fun invoke(networkconnectioninterceptor:NetworkConnectionInterceptor):MyApi{
            val okHttpclient =OkHttpClient.Builder().addInterceptor(networkconnectioninterceptor).build()

            return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .client(okHttpclient)
                .build().create(MyApi::class.java)
         }
    }
}