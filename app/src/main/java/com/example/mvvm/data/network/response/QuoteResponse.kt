package com.example.mvvm.data.network.response

import com.example.mvvm.data.db.entities.Quote

class QuoteResponse(val  isSuccessful:Boolean ,
                    val quotes:List<Quote>) {

}