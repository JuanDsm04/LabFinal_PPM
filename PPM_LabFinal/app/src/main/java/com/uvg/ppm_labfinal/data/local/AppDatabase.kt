package com.uvg.ppm_labfinal.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uvg.ppm_labfinal.data.local.dao.AssetDao
import com.uvg.ppm_labfinal.data.local.entity.AssetEntity

@Database(
    entities = [
        AssetEntity:: class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun assetDao(): AssetDao
}