package com.example.mvvm.ui.auth

import com.example.mvvm.data.db.entities.User

interface AuthListener {

    fun onStartINteraction()
    fun onSuccess(loginresponse: User)
    fun onFailure(s: String)
}