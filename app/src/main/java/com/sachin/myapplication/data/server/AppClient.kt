package com.sachin.myapplication.data.server

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppClient {

    private val getRetrofitQ: Retrofit by lazy {
        val builder = Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8081/")   // user ure base url
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .dispatcher(dispatcher)
            .build()

        builder.client(client).build()
    }

    @Singleton
    @Provides
    fun apiServer(): ApiServer = getRetrofitQ.create(ApiServer::class.java)
}