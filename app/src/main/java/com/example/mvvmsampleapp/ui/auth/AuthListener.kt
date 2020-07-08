package com.example.mvvmsampleapp.ui.auth

import androidx.lifecycle.LiveData
import com.example.mvvmsampleapp.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSucces(user: User)
    //fun onSucces(loginResponse: LiveData<String>)
    fun onFailure(message: String)
}