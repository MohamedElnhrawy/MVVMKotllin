package com.example.mvvm.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.mvvm.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        val navcontroller = Navigation.findNavController(this,R.id.fragment)
        NavigationUI.setupWithNavController(nav_view,navcontroller)
        NavigationUI.setupActionBarWithNavController(this,navcontroller,drawer_layout) //change title auto
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.fragment)
        ,drawer_layout)

    }
}
