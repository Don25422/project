package com.don.hustletracker.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.don.hustletracker.data.TaskDao
import com.don.hustletracker.model.Task
import com.don.hustletracker.navigation.ROUT_ADDTASK
import com.don.hustletracker.navigation.ROUT_WELCOME2
import com.don.hustletracker.repository.TaskRepository
import com.don.hustletracker.ui.theme.Deepblue
import com.don.hustletracker.ui.theme.card
import com.don.hustletracker.ui.theme.focused
import com.don.hustletracker.ui.theme.gold
import com.don.hustletracker.ui.theme.jetblack
import com.don.hustletracker.ui.theme.white
import com.don.hustletracker.viewmodel.TaskViewModel

@Composable
fun TaskScreen(navController: NavController, taskViewModel: TaskViewModel
) {
    val tasks by taskViewModel.tasks.collectAsState() // Get live tasks from ViewModel

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Your Tasks",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(tasks) { task ->
                var checked by remember { mutableStateOf(false) }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { isChecked ->
                                checked = isChecked
                                // Optional: handle completed tasks
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFFFFD700),
                                uncheckedColor = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = task.description,
                            color = if (checked) Color.Gray else Color.White,
                            textDecoration = if (checked) TextDecoration.LineThrough else TextDecoration.None,
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(onClick = {
                            taskViewModel.deleteTask(task)
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                        }
                    }
                }
            }
        }

        Button(onClick = {
            navController.navigate(ROUT_ADDTASK)

        },
            colors = ButtonDefaults.buttonColors(gold),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().padding(start =20.dp, end = 20.dp).height(50.dp)

        ) {
            Text( text = "Add Task",
                fontSize = 20.sp,
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun TaskScreenPreview() {
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

    TaskScreen(navController = rememberNavController(), taskViewModel = fakeViewModel)
}
