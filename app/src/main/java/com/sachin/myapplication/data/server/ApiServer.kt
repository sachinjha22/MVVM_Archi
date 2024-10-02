package com.sachin.myapplication.data.server

import com.sachin.myapplication.data.model.GR
import retrofit2.http.POST

interface ApiServer {
    @POST("auth/signUp") // user your api end point
    suspend fun getData(): GR
}