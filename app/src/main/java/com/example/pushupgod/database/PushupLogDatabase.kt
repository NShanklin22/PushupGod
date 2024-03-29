package com.example.pushupgod.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pushupgod.PushupLogDao

@Database(entities = [(PushupLog::class)], version = 1)
abstract class PushupLogDatabase: RoomDatabase() {

    abstract fun logDao(): PushupLogDao

    companion object {

        private var INSTANCE: PushupLogDatabase? = null

        fun getInstance(context: Context): PushupLogDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PushupLogDatabase::class.java,
                        "logDatabase"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}