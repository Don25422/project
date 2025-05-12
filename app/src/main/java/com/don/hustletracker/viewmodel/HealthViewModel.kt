package com.don.hustletracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.don.hustletracker.model.Health
import com.don.hustletracker.repository.HealthRepository
import kotlinx.coroutines.launch

open class HealthViewModel(private val repository: HealthRepository) : ViewModel() {

    var healthData = mutableListOf<Health>()

    fun loadHealth(onLoaded: (List<Health>) -> Unit) {
        viewModelScope.launch {
            healthData = repository.getAllHealth().toMutableList()
            onLoaded(healthData)
        }
    }

    open fun insertHealth(health: Health, onSaved: () -> Unit) {
        viewModelScope.launch {
            repository.insertHealth(health)
            onSaved()
        }
    }

    fun deleteHealth(health: Health, onDeleted: () -> Unit) {
        viewModelScope.launch {
            repository.deleteHealth(health)
            onDeleted()
        }
    }
}

class HealthViewModelFactory(private val repository: HealthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HealthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HealthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
