package com.sachin.myapplication.db.userdb

import com.sachin.myapplication.data.model.UM
import com.sachin.myapplication.data.repository.UserRepository
import javax.inject.Inject

class UserRepoImp @Inject constructor(private val userDao: UserDao) : UserRepository {

    override suspend fun getUsers(): List<UM?>? {
        return userDao.getUser()
    }

    override suspend fun insertUsers(users: ArrayList<UM>) {
        userDao.insert(users)
    }

    override suspend fun insertUsers(user: UM) {
        //// create fun in Dao and call here
    }

    override suspend fun deleteUser(user: UM) {
        userDao.delete(user.id)
    }

    override suspend fun updateUser(user: UM) {
        //// create fun in Dao and call here
    }

    override suspend fun clearAllUsers() {
        userDao.delete()
    }
}