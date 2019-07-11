package com.example.mvvm.data.repositories

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm.data.db.AppDatatBase
import com.example.mvvm.data.db.entities.Quote
import com.example.mvvm.data.db.entities.User
import com.example.mvvm.data.network.MyApi
import com.example.mvvm.data.network.SafeApiRequest
import com.example.mvvm.data.network.response.AuthResoinse
import com.example.mvvm.data.network.response.QuoteResponse
import com.example.mvvm.data.preferences.PreferencesProvider
import com.example.mvvm.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDate
import java.time.temporal.ChronoUnit

private val MINIMUM_INTERVAL=6

class QuotesRepository(private val api:MyApi,
                       private val db:AppDatatBase
                       ,private val preferences:PreferencesProvider
)  :SafeApiRequest(){
//  suspend fun getApiQuotes() :  QuoteResponse{
//        // you should inject this
//      return apiRequest { api.getQoutes()}
//    }


    suspend fun getQuotes(): LiveData<List<Quote>>{
        // withcontext as, we need acoroutine scope
        return withContext(Dispatchers.IO){
            getApiQuotes()
            db.getQuoteDao().getAllQuotes()
        }
    }

   private suspend fun getApiQuotes(){
        // you should inject this
        if (isFetchNeeded()){
            val response =apiRequest { api.getQoutes()}
            quotes.postValue(response.quotes)

        }
    }
    private val  quotes = MutableLiveData<List<Quote>>()
    init {
        // note observe used in activity or fragment , but oberceforever for else.
        quotes.observeForever{
            // wrong we can't call it in the main thread.
           // x-> db.getQuoteDao().insertAllQuotes(it)
            saveQuotes(it)
        }
    }

    private fun saveQuotes(quotes:List<Quote>){
        Coroutines.io{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                preferences.setDbTimeStamp(LocalDateTime.now().toString())
            }
            db.getQuoteDao().insertAllQuotes(quotes)
        }
    }

    private fun isFetchNeeded():Boolean{

        if (preferences.getTimeStamp() != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return ChronoUnit.HOURS.between(LocalDateTime.parse(preferences.getTimeStamp()),LocalDateTime.now())> MINIMUM_INTERVAL
            }
        }
        return true
    }

}