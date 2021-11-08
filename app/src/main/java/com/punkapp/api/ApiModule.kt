package com.punkapp.api

import com.punkapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    @Singleton
    @Provides
    fun providePunkApiService(client: OkHttpClient): BeerApiService {
        return Retrofit.Builder()
            .baseUrl("https://${BuildConfig.HOST_NAME}/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(BeerApiService::class.java)
    }
}