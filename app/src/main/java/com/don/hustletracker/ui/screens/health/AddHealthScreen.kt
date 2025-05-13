package com.don.hustletracker.ui.screens.health

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.don.hustletracker.model.Health
import com.don.hustletracker.ui.theme.gold
import com.don.hustletracker.ui.theme.jetblack
import com.don.hustletracker.ui.theme.white
import com.don.hustletracker.navigation.ROUT_HEALTH
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddHealthScreen(navController: NavController, onSave: (Health) -> Unit) {
    var activity by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(LocalDate.now().toString()) } // Added date field
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(jetblack)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Add Health Entry",
            style = TextStyle(color = white, fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = activity,
            onValueChange = { activity = it },
            label = { Text("Activity", color = white) },
            textStyle = TextStyle(color = white),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = duration,
            onValueChange = { duration = it },
            label = { Text("Duration (minutes)", color = white) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(color = white),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date (YYYY-MM-DD)", color = white) },
            textStyle = TextStyle(color = white),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val minutes = duration.toIntOrNull()
                if (activity.isBlank() || minutes == null || minutes <= 0 || date.isBlank()) {
                    Toast.makeText(context, "Please enter valid data", Toast.LENGTH_SHORT).show()
                } else {
                    val entry = Health(activity = activity, durationMinutes = minutes, date = date)
                    onSave(entry)
                    Toast.makeText(context, "Health entry saved", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUT_HEALTH) // changed to go to health screen
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = gold),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Save", color = white)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AddHealthScreenPreview() {
    AddHealthScreen(navController = rememberNavController()) {}
}
