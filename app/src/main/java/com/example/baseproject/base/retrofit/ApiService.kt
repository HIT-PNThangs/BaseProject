package com.example.baseproject.base.retrofit

import com.example.baseproject.base.entity.User
import retrofit2.http.GET

interface ApiService {
    @GET("/users")
    suspend fun getUsers(): List<User>
}