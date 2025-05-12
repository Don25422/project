package com.don.hustletracker.ui.screens.health

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.don.hustletracker.model.Health
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.don.hustletracker.viewmodel.HealthViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthScreen(navController: NavController, healthData: List<Health>) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Health Tracker") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("add_health")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Entry")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(healthData) { entry ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Activity: ${entry.activity}")
                        Text("Duration: ${entry.durationMinutes} minutes")
                        Text("Date: ${entry.date}")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HealthScreenPreview() {
    val dummyData = listOf(
        Health(activity = "Jogging", durationMinutes = 30, date = "2025-05-11"),
        Health(activity = "Yoga", durationMinutes = 45, date = "2025-05-10")
    )
    HealthScreen(navController = rememberNavController(), healthData = dummyData)
}

