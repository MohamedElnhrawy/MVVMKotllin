package com.example.mvvm.util

import kotlinx.coroutines.*

// create generic func

// will take ablock of asuspend function that should be executed inside coroutines scope and return type of suspend func is a generic type. and finally
//return type of the lazyDeferred func is Lazy of type Deferred of type T generic .
 public fun<T> lazyDeferred (block : suspend CoroutineScope.() -> T):Lazy<Deferred<T>>{
    return lazy {
        GlobalScope.async (start = CoroutineStart.LAZY){
            block.invoke(this)
        }
    }
}