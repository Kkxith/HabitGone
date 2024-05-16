package com.onyango.habits.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.onyango.habits.model.Habit

@Database(
    entities = [
        Habit::class
    ],
    version = 1,
    exportSchema = false
)
abstract class HabitDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao

    companion object {

        @Volatile // предотвратить гонку потоков
        private var INSTANCE: HabitDatabase? = null

        fun getDataBase(context: Context): HabitDatabase {

            // предотвращает создание одной БД двумя потоками одновременно
            synchronized(this) {

                val tempInstance = INSTANCE
                if (tempInstance != null) {
                    return tempInstance
                }

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HabitDatabase::class.java,
                    "habit_database"
                ).build()

                INSTANCE = instance
                return instance
            }

        }
    }
}