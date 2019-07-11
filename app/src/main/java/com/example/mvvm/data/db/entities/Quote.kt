package com.example.mvvm.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Quote (
    @PrimaryKey(autoGenerate = false)
    var id:Int,
    var quote:String,
    var author:String,
    var thumbnail:String,
    var created_at:String,
    var updated_aat:String
) {
}