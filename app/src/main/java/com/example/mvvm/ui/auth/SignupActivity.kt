package com.example.mvvm.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm.R
import com.example.mvvm.data.db.entities.User
import com.example.mvvm.databinding.ActivitySignupBinding
import com.example.mvvm.ui.home.HomeActivity
import com.example.mvvm.util.Hide
import com.example.mvvm.util.Show
import com.example.mvvm.util.toast
import kotlinx.android.synthetic.main.activity_signup.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity() ,KodeinAware ,AuthListener {
    override fun onStartINteraction() {
        register_progressBar.Show()
    }

    override fun onSuccess(loginresponse: User) {
        register_progressBar.Hide()
        toast(loginresponse.name!!)
        startActivity(Intent(this, HomeActivity::class.java))

    }

    override fun onFailure(s: String) {
        register_progressBar.Hide()
        toast(s)
    }

    override val kodein by kodein()
    val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_signup)

        val binding : ActivitySignupBinding = DataBindingUtil.setContentView(this,R.layout.activity_signup);
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.authViewModel=viewModel
        viewModel.loginListener = this


    }
}
