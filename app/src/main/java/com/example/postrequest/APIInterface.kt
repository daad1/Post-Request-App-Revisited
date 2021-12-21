package com.example.postrequest

import android.telecom.Call
import retrofit2.http.*

interface APIInterface {

        @GET("test/")
        fun getAll(): retrofit2.Call<Users>

        @POST("test/")
        fun addUser(@Body userData: UserItem): retrofit2.Call<UserItem>

        @PUT("/test/{id}")
        fun updateUser(@Path("id")id:Int, @Body personData :UserItem): retrofit2.Call<UserItem>


        @DELETE("/test/{id}")

        fun deleteUser (@Path("id")id:Int): retrofit2.Call<Void>
}