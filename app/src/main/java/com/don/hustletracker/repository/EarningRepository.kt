package com.don.hustletracker.repository

import com.don.hustletracker.data.EarningDao
import com.don.hustletracker.model.NewEarning
import kotlinx.coroutines.flow.Flow

class EarningRepository(private val dao: EarningDao) {
    val allEarnings: Flow<List<NewEarning>> = dao.getAllEarnings()

    suspend fun insert(earning: NewEarning) = dao.insertEarning(earning)
    suspend fun delete(earning: NewEarning) = dao.deleteEarning(earning)
}
