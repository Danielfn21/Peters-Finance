package com.example.peters_finance.api

import com.example.peters_finance.models.User
import retrofit2.Call
import retrofit2.http.GET

interface IUserService {

    @GET("users/")
    fun getUsers(): Call<MutableList<User>>

}