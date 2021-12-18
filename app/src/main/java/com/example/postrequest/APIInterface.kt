package com.example.postrequest

import android.telecom.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {

        @GET("test/")
        fun getAll(): retrofit2.Call<Users>

        @POST("test/")
        fun addUser(@Body userData: UserItem): retrofit2.Call<UserItem>
}