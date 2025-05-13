package com.don.hustletracker.ui.screens.task

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.don.hustletracker.model.Task
import com.don.hustletracker.navigation.ROUT_TASK
import com.don.hustletracker.repository.TaskRepository
import com.don.hustletracker.ui.theme.gold
import com.don.hustletracker.ui.theme.jetblack
import com.don.hustletracker.ui.theme.white
import com.don.hustletracker.viewmodel.TaskViewModel

@Composable
fun AddtaskScreen(navController: NavController, taskViewModel: TaskViewModel) {
    val context = LocalContext.current
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(jetblack)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Task Name", color = white) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(color = white),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = white,
                unfocusedBorderColor = white,
                cursorColor = white
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (description.isBlank()) {
                    Toast.makeText(context, "Please enter task", Toast.LENGTH_SHORT).show()
                } else {
                    taskViewModel.addTask(description)
                    Toast.makeText(context, "Task saved", Toast.LENGTH_SHORT).show()
                    description = ""
                    navController.navigate(ROUT_TASK) {
                        popUpTo(ROUT_TASK) { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = gold),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Save Task", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddtaskScreenPreview() {
    val fakeViewModel = object : TaskViewModel(
        repository = TaskRepository(object : com.don.hustletracker.data.TaskDao {
            override suspend fun insertTask(task: Task) {}
            override suspend fun deleteTask(task: Task) {}
            override suspend fun getAllTasks(): List<Task> = emptyList()
        })
    ) {}
    AddtaskScreen(navController = rememberNavController(), taskViewModel = fakeViewModel)
}
