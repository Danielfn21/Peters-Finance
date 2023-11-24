package com.example.peters_finance.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://localhost:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: IUserService by lazy {
        retrofit.create(IUserService::class.java)
    }
}

