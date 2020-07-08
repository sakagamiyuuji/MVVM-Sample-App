package com.example.mvvmsampleapp.data.network

import com.example.mvvmsampleapp.data.db.entities.User

data class AuthResponse (
    val isSuccesfull : Boolean?,
    val message : String?,
    val user : User?
)