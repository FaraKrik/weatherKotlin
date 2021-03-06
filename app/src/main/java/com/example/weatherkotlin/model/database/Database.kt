package com.example.weatherkotlin.model.database

import androidx.room.RoomDatabase

@androidx.room.Database(
    entities = [
        HistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}