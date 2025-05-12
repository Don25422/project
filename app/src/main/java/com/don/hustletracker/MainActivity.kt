package com.don.hustletracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.don.hustletracker.data.BusinessLogDatabase
import com.don.hustletracker.repository.BusinessLogRepository
import com.don.hustletracker.viewmodel.BusinessLogViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val database = BusinessLogDatabase.getDatabase(applicationContext)
            val repository = BusinessLogRepository(database.businessLogDao())
            val businessLogViewModel = BusinessLogViewModel(repository)



        }
    }
}

