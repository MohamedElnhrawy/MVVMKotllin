package com.example.mvvm.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.example.mvvm.data.db.entities.User
import com.example.mvvm.data.repositories.userRepository

class ProfileViewModel(repo:userRepository) : ViewModel() {
    // TODO: Implement the ViewModel

    val user : LiveData<User> = repo.getUser()
}
