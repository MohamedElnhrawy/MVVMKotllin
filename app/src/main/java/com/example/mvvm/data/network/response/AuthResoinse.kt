package com.example.mvvm.data.network.response

import com.example.mvvm.data.db.entities.User

data class AuthResoinse (
    val isSuccessful : Boolean?,
   val message:String?,
    val user:User?
)