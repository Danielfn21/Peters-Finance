package com.example.peters_finance.api

import android.util.Log
import com.example.peters_finance.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


suspend fun fetchUsers(): List<User> {
    var users = emptyList<User>()

        val retrofitClient = RetrofitClient()

        try {
            withContext(Dispatchers.IO) {
                val call = retrofitClient.api.getUsers()
                val response = call.execute()

                if (response.isSuccessful) {
                    users = response.body() ?: emptyList()
                } else {
                    // Log the error response if the request was not successful
                    Log.e("API_REQUEST", "Error: ${response.code()}")
                }
            }
        } catch (e: Exception) {
            // Log the exception
            Log.e("API_REQUEST", "Exception: $e")
        }

        return users
}