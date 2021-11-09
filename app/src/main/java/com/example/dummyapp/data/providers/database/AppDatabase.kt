package com.example.dummyapp.data.providers.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dummyapp.data.models.database.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}