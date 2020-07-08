package com.example.mvvmsampleapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmsampleapp.data.db.AppDatabase
import com.example.mvvmsampleapp.data.db.entities.User
import com.example.mvvmsampleapp.data.network.AuthResponse
import com.example.mvvmsampleapp.data.network.MyApi
import com.example.mvvmsampleapp.data.network.SafeApiRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(
    private val api : MyApi,
    private val db : AppDatabase
) : SafeApiRequest(){

    suspend fun userLogin(email: String, password: String) : AuthResponse/*Coroutines Response<AuthResponse>*/  /*LiveData<String>*/ {

        //Sesudah injection
        return apiRequest {api.userLogin(email, password)}

        /*Sebelum injection
        return apiRequest {MyApi().userLogin(email, password)}*/

        /*Coroutines
        return MyApi().userLogin(email, password)*/

        /* LIVE DATA
        val loginResponse = MutableLiveData<String>()

        MyApi().userLogin(email, password)
            .enqueue(object: Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful){
                        loginResponse.value = response.body()?.string()
                    } else{
                        loginResponse.value = response.errorBody()?.toString()

                    }
                }

            })

        return loginResponse*/
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}