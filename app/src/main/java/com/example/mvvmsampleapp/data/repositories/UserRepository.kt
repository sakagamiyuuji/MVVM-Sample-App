package com.example.mvvmsampleapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmsampleapp.data.network.AuthResponse
import com.example.mvvmsampleapp.data.network.MyApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    suspend fun userLogin(email: String, password: String) : Response<AuthResponse>/*LiveData<String>*/ {
        return MyApi().userLogin(email, password)

/*        val loginResponse = MutableLiveData<String>()

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
}