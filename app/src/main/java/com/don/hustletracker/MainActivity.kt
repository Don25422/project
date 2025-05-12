package com.don.hustletracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.don.hustletracker.data.BusinessLogDatabase
import com.don.hustletracker.navigation.AppNavHost
import com.don.hustletracker.repository.BusinessLogRepository
import com.don.hustletracker.viewmodel.BusinessLogViewModel
import com.don.hustletracker.viewmodel.EarningViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val earningViewModel : EarningViewModel = viewModel()
            AppNavHost(navController = navController, earningViewModel = earningViewModel)




        }
    }
}

