package com.example.mvvmsampleapp.util

import java.io.IOException

class ApiExcepetion(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)