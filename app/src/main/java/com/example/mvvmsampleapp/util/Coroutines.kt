package com.example.mvvmsampleapp.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {

//unit mirip void java
    fun main(work: suspend (()  -> Unit )) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }
}