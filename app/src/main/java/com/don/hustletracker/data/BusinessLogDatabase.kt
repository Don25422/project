package com.don.hustletracker.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.don.hustletracker.model.BusinessLog

@Database(entities = [BusinessLog::class], version = 1, exportSchema = false)
abstract class BusinessLogDatabase : RoomDatabase() {
    abstract fun businessLogDao(): BusinessLogDao

    companion object {
        @Volatile
        private var INSTANCE: BusinessLogDatabase? = null

        fun getDatabase(context: Context): BusinessLogDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BusinessLogDatabase::class.java,
                    "business_log_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
