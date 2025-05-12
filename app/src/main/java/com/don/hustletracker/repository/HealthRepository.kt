package com.don.hustletracker.repository

import com.don.hustletracker.data.HealthDao
import com.don.hustletracker.model.Health

class HealthRepository(private val dao: HealthDao) {

    suspend fun insertHealth(health: Health) {
        dao.insertHealthData(health)
    }

    suspend fun getAllHealth(): List<Health> {
        return dao.getAllHealthData()
    }

    suspend fun deleteHealth(health: Health) {
        dao.deleteHealthData(health)
    }
}
