package com.example.mvvm.ui.quotes

import androidx.lifecycle.ViewModel;
import com.example.mvvm.data.repositories.QuotesRepository
import com.example.mvvm.util.lazyDeferred
import org.kodein.di.LazyDelegate
import kotlin.properties.Delegates

class QuotesViewModel(repository: QuotesRepository) : ViewModel() {

    // 1- this means once this viewmodel created it will getQuotes even if we needn't it ,so we will use by lazy
   // 2-  val quotes by lazy{
        // we need to call it from coroutines scope so we need to handle custom lazy block to use coroutines scope first using delegates .
       // repository.getQuotes()
    //}

    val quotes by lazyDeferred{
        repository.getQuotes()
    }

}
