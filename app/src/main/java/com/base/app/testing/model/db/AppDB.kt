package com.base.app.testing.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GymData::class, PopularGymData::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun daoApp(): DaoApp

    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getDatabaseClient(context: Context): AppDB {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {
                INSTANCE = Room.databaseBuilder(context, AppDB::class.java, "GYM_DATABASE.db")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!
            }
        }
    }
}