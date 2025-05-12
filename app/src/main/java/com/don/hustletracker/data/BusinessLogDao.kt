package com.don.hustletracker.data


import androidx.room.*
import com.don.hustletracker.model.BusinessLog

@Dao
interface BusinessLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: BusinessLog)

    @Update
    suspend fun updateLog(log: BusinessLog)

    @Delete
    suspend fun deleteLog(log: BusinessLog)

    @Query("SELECT * FROM business_logs ORDER BY id DESC")
    suspend fun getAllLogs(): List<BusinessLog>
}
