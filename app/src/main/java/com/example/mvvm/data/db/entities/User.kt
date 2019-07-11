package com.example.mvvm.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID=0


@Entity
data class User (
    var id:String?,
    var name:String?,
    var email:String? ,
    var email_verified_at:String? ,
    var created_at:String? ,
    var updated_at:String?
) {

    @PrimaryKey(autoGenerate = false)
    var uid:Int = CURRENT_USER_ID

}