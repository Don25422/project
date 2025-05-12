package com.don.hustletracker.repository

import com.don.hustletracker.data.TaskDao
import com.don.hustletracker.model.Task

class TaskRepository(private val dao: TaskDao) {
    suspend fun insertTask(task: Task) = dao.insertTask(task)
    suspend fun getAllTasks() = dao.getAllTasks()
    suspend fun deleteTask(task: Task) = dao.deleteTask(task)
}
