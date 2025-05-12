package com.don.hustletracker.repository


import com.don.hustletracker.data.BusinessLogDao
import com.don.hustletracker.model.BusinessLog

class BusinessLogRepository(private val dao: BusinessLogDao) {
    suspend fun insertLog(log: BusinessLog) = dao.insertLog(log)
    suspend fun updateLog(log: BusinessLog) = dao.updateLog(log)
    suspend fun deleteLog(log: BusinessLog) = dao.deleteLog(log)
    suspend fun getAllLogs(): List<BusinessLog> = dao.getAllLogs()
}
