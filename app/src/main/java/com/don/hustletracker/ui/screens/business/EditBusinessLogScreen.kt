package com.don.hustletracker.ui.screens.business

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.don.hustletracker.model.BusinessLog // ✅ Make sure this import is correct

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBusinessLogScreen(
    log: BusinessLog,
    onUpdate: (BusinessLog) -> Unit
) {
    var title by remember { mutableStateOf(log.title) }
    var description by remember { mutableStateOf(log.description) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Edit Log") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onUpdate(log.copy(title = title, description = description))
            }) {
                Text("✓")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEditBusinessLogScreen() {
    EditBusinessLogScreen(
        log = BusinessLog(2, "Sale", "Sold 10 units"),
        onUpdate = {}
    )
}

fun EditBusinessLogScreen(log: com.don.hustletracker.ui.screens.business.BusinessLog, onUpdate: (BusinessLog) -> Unit) {


}
