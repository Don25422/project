package com.don.hustletracker.ui.screens.earn

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.don.hustletracker.model.NewEarning
import com.don.hustletracker.viewmodel.EarningViewModel

@Composable
fun AddEarningScreen(
    navController: NavController? = null,
    viewModel: EarningViewModel? = null
) {
    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Add Earning", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val amountDouble = amount.toDoubleOrNull()
                if (description.isNotBlank() && amountDouble != null && viewModel != null) {
                    viewModel.insertEarning(NewEarning(description = description, amount = amountDouble))
                    navController?.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddEarningScreenPreview() {
    AddEarningScreen()
}
