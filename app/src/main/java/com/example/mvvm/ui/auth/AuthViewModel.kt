package com.example.mvvm.ui.auth

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.data.db.entities.User
import com.example.mvvm.data.repositories.userRepository
import com.example.mvvm.util.ApiException
import com.example.mvvm.util.Coroutines
import com.example.mvvm.util.NoInternetException

class AuthViewModel(private val respository:userRepository) : ViewModel() {

    var loginListener : AuthListener? = null
    var loading : MutableLiveData<Boolean> =  MutableLiveData()

    //= false as MutableLiveData<Boolean>
    var email:String? = null
    var pass:String? = null
    var confirm_pass:String? = null
    var name:String? = null

    fun onLoginBtnClicked(view: View){
        loginListener?.onStartINteraction()
        if (email.isNullOrEmpty() || pass.isNullOrEmpty() ){
            //fail
            loginListener?.onFailure("empty data")
            return
        }


        // succ
        Coroutines.main{
            try {
                val  response = respository.userLogin(email!!, pass!!)
                response.user?.let { loginListener?.onSuccess(it)
                    respository.saveUser(it)
                return@main
                }
                loginListener?.onFailure(response.message!!)

            }catch (e: ApiException){
                loginListener?.onFailure(e.message!!)
            }catch (e:NoInternetException){
                loginListener?.onFailure(e.message!!)
            }
        }
        // should inject repo


    }


    fun onSignupBtnClicked(view: View){
        loginListener?.onStartINteraction()
        if (name.isNullOrEmpty() ){
            //fail
            loginListener?.onFailure("name is required")
            return
        }
        if (email.isNullOrEmpty() ){
            //fail
            loginListener?.onFailure("email is required")
            return
        }
        if (pass.isNullOrEmpty() ){
            //fail
            loginListener?.onFailure("password is required")
            return
        }

        if (confirm_pass.isNullOrEmpty() ){
            //fail
            loginListener?.onFailure("confirm password is required")
            return
        }

        if (!pass.equals(confirm_pass)){
            loginListener?.onFailure("password don't matches")
            return
        }

        // succ
        Coroutines.main{
            try {
                val  response = respository.userSignup(name!!,email!!, pass!!)
                response.user?.let { loginListener?.onSuccess(it)
                    respository.saveUser(it)
                    return@main
                }
                loginListener?.onFailure(response.message!!)

            }catch (e: ApiException){
                loginListener?.onFailure(e.message!!)
            }catch (e:NoInternetException){
                loginListener?.onFailure(e.message!!)
            }
        }
        // should inject repo


    }




    fun openSignup(view:View){
        view.context.startActivity(Intent(view.context,SignupActivity::class.java))
    }
    fun openLogin(view:View){
        (view.context as Activity ).finish()
        Log.e("cc","ccc")
    }

    fun getLoadingState(): LiveData<Boolean> = loading

    fun getLoggenInUser() : LiveData<User>{
        return respository.getUser()
    }
}