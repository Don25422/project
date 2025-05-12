package com.don.hustletracker.data

import androidx.room.*
import com.don.hustletracker.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EarningDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEarning(earning: NewEarning)

    @Delete
    suspend fun deleteEarning(earning: NewEarning)

    @Query("SELECT * FROM new_earnings ORDER BY id DESC")
    fun getAllEarnings(): Flow<List<NewEarning>>
}
