package com.sachin.myapplication.db.userdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sachin.myapplication.data.model.UM

@Database(entities = [UM::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

}