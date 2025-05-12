package com.don.hustletracker.ui.screens.earn

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.don.hustletracker.model.NewEarning
import com.don.hustletracker.viewmodel.EarningViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EarningScreen(viewModel: EarningViewModel) {
    val earnings by viewModel.allEarnings.collectAsState()

    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        BasicTextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            decorationBox = { innerTextField ->
                Box(Modifier.padding(8.dp)) {
                    if (description.isEmpty()) Text("Description")
                    innerTextField()
                }
            }
        )

        BasicTextField(
            value = amount,
            onValueChange = { amount = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            decorationBox = { innerTextField ->
                Box(Modifier.padding(8.dp)) {
                    if (amount.isEmpty()) Text("Amount")
                    innerTextField()
                }
            }
        )

        Button(onClick = {
            val amt = amount.toDoubleOrNull()
            if (description.isNotBlank() && amt != null) {
                viewModel.insertEarning(NewEarning(description = description, amount = amt))
                description = ""
                amount = ""
            }
        }) {
            Text("Save Earning")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(earnings.size) { index ->
                val earn = earnings[index]
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${earn.description}: ${earn.amount}")
                    IconButton(onClick = { viewModel.deleteEarning(earn) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEarningScreen() {
    val navController = rememberNavController()

    EarningScreen(navController = navController)
}

fun EarningScreen(navController: NavHostController) {


}
