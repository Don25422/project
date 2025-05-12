package com.don.hustletracker.ui.screens.task

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.don.hustletracker.data.AppDatabase
import com.don.hustletracker.data.TaskDao
import com.don.hustletracker.model.Task
import com.don.hustletracker.repository.TaskRepository
import com.don.hustletracker.ui.theme.gold
import com.don.hustletracker.ui.theme.jetblack
import com.don.hustletracker.ui.theme.white
import com.don.hustletracker.viewmodel.TaskViewModel
import com.don.hustletracker.viewmodel.TaskViewModelFactory

@Composable
fun AddtaskScreen(navController: NavController, taskViewModel: Any) {
    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)
    val repository = TaskRepository(database.taskDao())
    val taskViewModel: TaskViewModel = viewModel(
        factory = TaskViewModelFactory(repository)
    )
    var description by remember { mutableStateOf("") }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(jetblack),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = {
                Text(
                    "Task Name",
                    color = white,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(color = white)

        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (description.isBlank()) {
                    Toast.makeText(context, "Please enter task", Toast.LENGTH_SHORT).show()
                } else {
                    taskViewModel.addTask(description)  // ✅ Calls ViewModel to save task to DB
                    Toast.makeText(context, "Task saved", Toast.LENGTH_SHORT).show()
                    description = "" // Clear input after saving
                    navController.popBackStack() // Optionally go back after saving
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
fun AddtaskScreenPreview(){
    val fakeViewModel = object : TaskViewModel(
        repository = TaskRepository(object : TaskDao {
            override suspend fun insertTask(task: Task) {}
            override suspend fun deleteTask(task: Task) {}
            override suspend fun getAllTasks(): List<Task> = listOf(
                Task(1, "Preview Task 1"),
                Task(2, "Preview Task 2")
            )
        })
    ) {}
    AddtaskScreen(navController= rememberNavController(), taskViewModel = fakeViewModel)
}