package com.don.hustletracker.ui.screens.business

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.don.hustletracker.model.BusinessLog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBusinessLogScreen(
    onSave: (BusinessLog) -> Unit  // ✅ Corrected type
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Add Business Log") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (title.isNotBlank() && description.isNotBlank()) {
                    onSave(BusinessLog(0, title, description))  // ✅ Now this works
                }
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

fun onSave(businessLog: com.don.hustletracker.ui.screens.business.BusinessLog) {


}

@Preview(showBackground = true)
@Composable
fun PreviewAddBusinessLogScreen() {
    AddBusinessLogScreen(
        onSave = {}  // ✅ Use empty lambda for preview
    )
}
