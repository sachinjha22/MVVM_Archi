package com.sachin.myapplication.data.repository

import com.sachin.myapplication.data.model.UM

interface UserRepository {
    suspend fun getUsers(): List<UM?>?
    suspend fun insertUsers(users: ArrayList<UM>)
    suspend fun insertUsers(user: UM)
    suspend fun deleteUser(user: UM)
    suspend fun updateUser(user: UM)
    suspend fun clearAllUsers()
}