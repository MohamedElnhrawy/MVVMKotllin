package com.example.mvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm.data.db.entities.Quote

@Dao
interface QuoteDao {
    // removed suspend till later
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAllQuotes (quotes: List<Quote>)

    @Query("select * from Quote")
    fun getAllQuotes():LiveData<List<Quote>>
}