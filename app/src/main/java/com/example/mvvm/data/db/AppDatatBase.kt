package com.example.mvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm.data.db.entities.Quote
import com.example.mvvm.data.db.entities.User

@Database(entities = [User::class , Quote::class],
    version = 1)
abstract class AppDatatBase : RoomDatabase() {
    abstract fun getUserDao():UserDao
    abstract fun getQuoteDao(): QuoteDao

    companion object{
        @Volatile
        private var instance:AppDatatBase?=null

        private val Lock=Any()

        operator fun invoke(context:Context)= instance?: synchronized(Lock){
            instance?:buildDatabase(context).also { instance=it }
        }

      private  fun buildDatabase(context: Context)=
                Room.databaseBuilder(context.applicationContext,AppDatatBase::class.java
                ,"Mydatabase.db").build()
    }
}