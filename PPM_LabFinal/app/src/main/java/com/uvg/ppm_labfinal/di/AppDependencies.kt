package com.uvg.ppm_labfinal.di

import android.content.Context
import androidx.room.Room
import com.uvg.ppm_labfinal.data.remote.HttpClientFactory
import com.uvg.ppm_labfinal.data.local.AppDatabase

import io.ktor.client.HttpClient

object AppDependencies {
    private var database: AppDatabase? = null
    private var httpClient: HttpClient? = null

    private fun buildHttpClient(): HttpClient = HttpClientFactory.create()

    private fun buildDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "assets.db"

        ).build()
    }

    fun provideDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            database ?: buildDatabase(context).also { database = it }
        }
    }

    fun provideHttpClient(): HttpClient {
        return httpClient ?: synchronized(this) {
            httpClient ?: buildHttpClient().also { httpClient = it }
        }
    }
}