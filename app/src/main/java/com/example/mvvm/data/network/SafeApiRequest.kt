package com.example.mvvm.data.network

import android.util.Log
import com.example.mvvm.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.lang.IllegalArgumentException
import java.lang.StringBuilder

abstract  class SafeApiRequest {

    suspend fun <T : Any>   apiRequest(call : suspend () -> Response<T> ) : T {

        val response = call.invoke()
        if (response.isSuccessful)
            return response.body()!!
        else{
            // handel error
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))

                }catch (e: JSONException) {
                    Log.e("Exception","JSON")

                }catch (e:IOException){
                    Log.e("Exception","IO")
                }
                message.append("\n")
            }
            message.append("Error Code: ${response.code()}")
            throw ApiException(message.toString())
            throw IOException("IOException")
            throw IllegalArgumentException("IllegalArgumentException")
        }

    }
}