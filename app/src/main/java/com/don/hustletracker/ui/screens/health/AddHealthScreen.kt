package com.don.hustletracker.ui.screens.health
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.don.hustletracker.model.Health
import com.don.hustletracker.viewmodel.HealthViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.don.hustletracker.data.HealthDao
import com.don.hustletracker.repository.HealthRepository
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddHealthScreen(navController: NavController, onSave: (Health) -> Unit) {
    var activity by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Add Health Entry", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = activity,
            onValueChange = { activity = it },
            label = { Text("Activity") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = duration,
            onValueChange = { duration = it },
            label = { Text("Duration (minutes)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val minutes = duration.toIntOrNull() ?: 0
            val entry = Health(activity = activity, durationMinutes = minutes, date = LocalDate.now().toString())
            onSave(entry)
            navController.popBackStack()
        }, modifier = Modifier.align(Alignment.End)) {
            Text("Save")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AddHealthScreenPreview() {

    val navController = rememberNavController()
    AddHealthScreen(navController = navController)
}

fun AddHealthScreen(navController: NavHostController) {

}

