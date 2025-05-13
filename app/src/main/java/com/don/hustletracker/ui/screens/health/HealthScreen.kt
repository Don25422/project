package com.don.hustletracker.ui.screens.health

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.don.hustletracker.model.Health
import com.don.hustletracker.navigation.ROUT_ADDHEALTH
import com.don.hustletracker.ui.theme.gold
import com.don.hustletracker.ui.theme.jetblack
import com.don.hustletracker.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthScreen(navController: NavController, healthData: List<Health>) {
    Scaffold(
        containerColor = jetblack,
        topBar = {
            TopAppBar(
                title = { Text("Health Tracker", color = white) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = jetblack)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(ROUT_ADDHEALTH) },
                containerColor = gold,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Entry")
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .background(jetblack)
        ) {
            items(healthData) { entry ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Activity: ${entry.activity}", color = white)
                        Text("Duration: ${entry.durationMinutes} minutes", color = white)
                        Text("Date: ${entry.date}", color = white)
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
