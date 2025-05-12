package com.don.hustletracker.repository

import com.don.hustletracker.data.EarningDao
import com.don.hustletracker.model.Earning
import kotlinx.coroutines.flow.Flow

class EarningRepository(private val dao: EarningDao) {
    val allEarnings: Flow<List<Earning>> = dao.getAllEarnings()

    suspend fun insert(earning: Earning) = dao.insertEarning(earning)
    suspend fun delete(earning: Earning) = dao.deleteEarning(earning)
}
