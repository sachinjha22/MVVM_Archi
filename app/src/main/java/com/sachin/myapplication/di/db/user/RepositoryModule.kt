package com.sachin.myapplication.di.db.user

import com.sachin.myapplication.data.repository.UserRepository
import com.sachin.myapplication.db.userdb.UserRepoImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindTaskRepo(taskRepository: UserRepoImp) : UserRepository
}