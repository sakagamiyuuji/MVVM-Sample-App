package com.example.mvvmsampleapp.data.network

import android.util.Log
import com.example.mvvmsampleapp.util.ApiExcepetion
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest{

    suspend fun<T: Any> apiRequest(call: suspend() -> Response<T>) : T{
        val response = call.invoke()

        if (response.isSuccessful){
            return response.body()!!
        } else{
            val error = response.errorBody()?.string()
            if (error != null) {
                Log.d("errorBody", error)
            }

            val message = StringBuilder()
            error?.let {
                try {
                    val messages = JSONObject(it).getString("message")
                    message.append(messages)
                    Log.d("errorMessageJson", messages)
                }
                catch (e: JSONException){ }
                message.append("\n")
            }

            message.append("Error Code: ${response.code()}")
            Log.d("errorCode", "${response.code()}")

            throw ApiExcepetion(message.toString())
        }


    }
}