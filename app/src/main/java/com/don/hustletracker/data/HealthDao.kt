package com.don.hustletracker.data

import androidx.room.*
import com.don.hustletracker.model.Health

@Dao
interface HealthDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHealthData(health: Health)

    @Query("SELECT * FROM health_data ORDER BY id DESC")
    suspend fun getAllHealthData(): List<Health>

    @Delete
    suspend fun deleteHealthData(health: Health)
}
