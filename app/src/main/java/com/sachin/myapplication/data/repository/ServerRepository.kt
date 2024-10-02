package com.sachin.myapplication.data.repository

import com.sachin.myapplication.data.server.ApiServer
import javax.inject.Inject

class ServerRepository @Inject constructor(private val apiServer: ApiServer) {
    suspend fun getData() = apiServer.getData()
}