package com.sachin.myapplication.db.userdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sachin.myapplication.data.model.UM

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: ArrayList<UM>)

    @Query("SELECT * FROM um")
    fun getUser(): List<UM?>?

    @Query("DELETE FROM um")
    fun delete()
}