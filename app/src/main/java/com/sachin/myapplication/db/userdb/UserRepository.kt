package com.sachin.myapplication.db.userdb

import com.sachin.myapplication.data.model.UM
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insertUser(user: ArrayList<UM>) {
        userDao.insert(user)
    }

    suspend fun getAllUsers(): List<UM?>? {
        return userDao.getUser()
    }

    suspend fun deleteUser() {
        userDao.delete()
    }
}