package com.example.mvvm.data.repositories

import com.example.mvvm.data.db.AppDatatBase
import com.example.mvvm.data.db.entities.User
import com.example.mvvm.data.network.MyApi
import com.example.mvvm.data.network.SafeApiRequest
import com.example.mvvm.data.network.response.AuthResoinse

class userRepository(private val api:MyApi , private val db:AppDatatBase)  :SafeApiRequest(){


  suspend fun userLogin(email:String, password:String) : AuthResoinse {
        // you should inject this
      return apiRequest { api.Login(email,password)}
    }

    suspend fun saveUser(user: User) = db.getUserDao().insert(user)

     fun getUser() = db.getUserDao().getUser()

    // signup
    suspend fun userSignup(name:String ,email:String, password:String) : AuthResoinse{
        return apiRequest{api.Signup(name,email,password)}
    }





}