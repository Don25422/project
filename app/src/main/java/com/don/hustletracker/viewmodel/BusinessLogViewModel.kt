package com.don.hustletracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.don.hustletracker.model.BusinessLog
import com.don.hustletracker.repository.BusinessLogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BusinessLogViewModel(private val repository: BusinessLogRepository) : ViewModel() {

    private val _logs = MutableStateFlow<List<BusinessLog>>(emptyList())
    val logs: StateFlow<List<BusinessLog>> = _logs

    init {
        loadLogs()
    }

    fun loadLogs() {
        viewModelScope.launch {
            _logs.value = repository.getAllLogs()
        }
    }

    fun addLog(log: BusinessLog) {
        viewModelScope.launch {
            repository.insertLog(log)
            loadLogs()
        }
    }

    fun updateLog(log: BusinessLog) {
        viewModelScope.launch {
            repository.updateLog(log)
            loadLogs()
        }
    }

    fun deleteLog(log: BusinessLog) {
        viewModelScope.launch {
            repository.deleteLog(log)
            loadLogs()
        }
    }
}

class BusinessLogViewModelFactory(private val repository: BusinessLogRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusinessLogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BusinessLogViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
