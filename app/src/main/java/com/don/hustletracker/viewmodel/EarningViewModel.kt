package com.don.hustletracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.don.hustletracker.data.EarningDatabase
import com.don.hustletracker.model.Earning
import com.don.hustletracker.repository.EarningRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

open class EarningViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: EarningRepository

    open val allEarnings = EarningDatabase.getDatabase(application).earningDao()
        .getAllEarnings().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        val dao = EarningDatabase.getDatabase(application).earningDao()
        repo = EarningRepository(dao)
    }

    fun insertEarning(earning: Earning) = viewModelScope.launch {
        repo.insert(earning)
    }

    fun deleteEarning(earning: Earning) = viewModelScope.launch {
        repo.delete(earning)
    }
}
