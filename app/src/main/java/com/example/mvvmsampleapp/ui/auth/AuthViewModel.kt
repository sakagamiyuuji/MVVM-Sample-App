package com.example.mvvmsampleapp.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleapp.data.repositories.UserRepository
import com.example.mvvmsampleapp.util.ApiExcepetion
import com.example.mvvmsampleapp.util.Coroutines
import com.example.mvvmsampleapp.util.NoInternetException
import kotlin.math.log

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var email: String? = null
    var password:String? = null

    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View){
        authListener?.onStarted()

        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }

        Coroutines.main {
            try {

                val authResponse = repository.userLogin(email!!, password!!)
                //Sebelum injection
                //val authResponse = UserRepository().userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSucces(it)
                    Log.d("userCheck", it.toString())
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)


            } catch (e: ApiExcepetion){
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }



            //COROUTINES
            /*val response =  UserRepository().userLogin(email!!, password!!)
            if (response.isSuccessful){
                authListener?.onSucces(response.body()?.user!!)
            } else{
                authListener?.onFailure("Error Code: ${response.code()}")
            }*/
        }



/*        val loginResponse = UserRepository().userLogin(email!!, password!!)
        authListener?.onSucces(loginResponse)*/

    }

}