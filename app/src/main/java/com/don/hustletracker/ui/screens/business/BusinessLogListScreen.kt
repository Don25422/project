package com.don.hustletracker.ui.screens.business


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.don.hustletracker.viewmodel.BusinessLogViewModel

data class BusinessLog(val id: Int, val title: String, val description: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessLogListScreen(logs: NavHostController, onLogClick: BusinessLogViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Business Logs") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* navigate to AddLogScreen */ }) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(logs) { log ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { onLogClick(log) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = log.title, style = MaterialTheme.typography.titleMedium)
                        Text(text = log.description, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBusinessLogListScreen() {
    val sampleLogs = listOf(
        BusinessLog(1, "Inventory Purchase", "Bought 50 items"),
        BusinessLog(2, "Customer Order", "Sold 5 items")
    )
    BusinessLogListScreen(logs = sampleLogs, onLogClick = {})
}
