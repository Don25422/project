package com.don.hustletracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.don.hustletracker.model.NewEarning

@Database(entities = [NewEarning::class], version = 1)
abstract class EarningDatabase : RoomDatabase() {
    abstract fun earningDao(): EarningDao

    companion object {
        @Volatile private var INSTANCE: EarningDatabase? = null

        fun getDatabase(context: Context): EarningDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EarningDatabase::class.java,
                    "new_earning_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
