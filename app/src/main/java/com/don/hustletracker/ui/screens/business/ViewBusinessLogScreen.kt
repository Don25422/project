package com.don.hustletracker.ui.screens.business

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.don.hustletracker.model.BusinessLog // ✅ Ensure this import is correct

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewBusinessLogScreen(log: BusinessLog) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("View Log") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(text = "Title: ${log.title}", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Description: ${log.description}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewViewBusinessLogScreen() {
    ViewBusinessLogScreen(BusinessLog(1, "Delivery", "Delivered 20 packages"))
}

fun ViewBusinessLogScreen(log: com.don.hustletracker.ui.screens.business.BusinessLog) {




}
