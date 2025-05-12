package com.don.hustletracker.ui.screens.earning

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.don.hustletracker.navigation.ROUT_ADDEARNING
import com.don.hustletracker.ui.theme.jetblack
import com.don.hustletracker.ui.theme.white
import com.don.hustletracker.viewmodel.EarningViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EarningScreen(
    viewModel: EarningViewModel,
    navController: NavHostController
) {
    val earnings by viewModel.allEarnings.collectAsStateWithLifecycle()
    val totalEarnings = earnings.sumOf { it.amount }
    val goal = 10000.0 // Example goal

    Scaffold(
        topBar = { TopAppBar(title = { Text("Earnings",color = white) }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(ROUT_ADDEARNING)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Earning")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(jetblack)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text("Total Earnings: Ksh $totalEarnings", style = MaterialTheme.typography.headlineSmall,color = white)
            Spacer(modifier = Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = (totalEarnings / goal).toFloat().coerceIn(0f, 1f),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text("Earning History", style = MaterialTheme.typography.titleMedium,color = white)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(earnings) { earning ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "Ksh ${earning.amount}", style = MaterialTheme.typography.bodyLarge)
                            Text(text = earning.description, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
