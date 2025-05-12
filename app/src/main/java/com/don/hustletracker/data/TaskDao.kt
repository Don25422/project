package com.don.hustletracker.data

import androidx.room.*
import com.don.hustletracker.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    suspend fun getAllTasks(): List<Task>

    @Delete
    suspend fun deleteTask(task: Task)
}
