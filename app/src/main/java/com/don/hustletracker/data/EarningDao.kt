package com.don.hustletracker.data

import androidx.room.*
import com.don.hustletracker.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EarningDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEarning(earning: Earning)

    @Delete
    suspend fun deleteEarning(earning: Earning)

    @Query("SELECT * FROM earnings ")
    fun getAllEarnings(): Flow<List<Earning>>
}
