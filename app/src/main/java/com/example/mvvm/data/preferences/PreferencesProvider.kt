package com.example.mvvm.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

private const val TIME_STAMP_KEY="time_stamp_key"
class PreferencesProvider(context:Context) {

    private val appcontext = context.applicationContext
    private val preference : SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(appcontext)

    fun setDbTimeStamp(time : String){
        preference.edit().putString(TIME_STAMP_KEY,time).apply()
    }

    fun getTimeStamp():String?{
        return preference.getString(TIME_STAMP_KEY,null);
    }
}