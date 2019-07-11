package com.example.mvvm.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm.R
import com.example.mvvm.data.db.entities.User
import com.example.mvvm.databinding.ActivityLoginBinding
import com.example.mvvm.ui.home.HomeActivity
import com.example.mvvm.util.Hide
import com.example.mvvm.util.Show
import com.example.mvvm.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener ,KodeinAware{

    override val kodein by kodein()
    val factory : AuthViewModelFactory by instance()

    override fun onStartINteraction() {
        login_progressBar.Show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding:ActivityLoginBinding  = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.authViewModel=viewModel
        viewModel.loginListener=this
        viewModel.getLoggenInUser().observe(this, Observer { user ->
            if(user != null){
                startActivity(Intent(this, HomeActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                        or Intent.FLAG_ACTIVITY_NEW_TASK))
            }
        })

    }

    override fun onSuccess(user: User) {
        toast("success")
        Log.e("success","success")
            login_progressBar.Hide()
            toast(user.name!!)
        startActivity(Intent(this,HomeActivity::class.java))


    }

    override fun onFailure(s: String) {
        login_progressBar.Hide()
        toast("error $s")
        Log.e("error",s)

    }

}

