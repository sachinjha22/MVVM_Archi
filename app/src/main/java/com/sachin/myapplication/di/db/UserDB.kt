package com.sachin.myapplication.di.db

import android.content.Context
import androidx.room.Room
import com.sachin.myapplication.db.userdb.UserDao
import com.sachin.myapplication.db.userdb.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDB {
    @Provides
    @Singleton
    fun providerUserDatabase(@ApplicationContext appContext: Context): UserDatabase {
        return Room.databaseBuilder(appContext, UserDatabase::class.java, "user_db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideUserDao(database: UserDatabase): UserDao = database.userDao()
}