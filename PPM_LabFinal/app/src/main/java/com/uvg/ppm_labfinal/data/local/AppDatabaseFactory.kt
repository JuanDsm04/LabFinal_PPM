package com.uvg.ppm_labfinal.data.local

import android.content.Context
import androidx.room.Room

object AppDatabaseFactory {
    fun create(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "assets.db"
        ).build()
    }
}