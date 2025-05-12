package com.don.hustletracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.don.hustletracker.data.AppDatabase
import com.don.hustletracker.data.BusinessLogDatabase
import com.don.hustletracker.data.UserDatabase
import com.don.hustletracker.model.Health
import com.don.hustletracker.repository.BusinessLogRepository
import com.don.hustletracker.repository.TaskRepository
import com.don.hustletracker.repository.UserRepository
import com.don.hustletracker.ui.screens.about.AboutScreen
import com.don.hustletracker.ui.screens.auth.LoginScreen
import com.don.hustletracker.ui.screens.auth.RegisterScreen
import com.don.hustletracker.ui.screens.dashboard.DashboardScreen
import com.don.hustletracker.ui.screens.earn.AddEarningScreen
import com.don.hustletracker.ui.screens.earn.EarningScreen
import com.don.hustletracker.ui.screens.health.AddHealthScreen
import com.don.hustletracker.ui.screens.health.HealthScreen
import com.don.hustletracker.ui.screens.home.HomeScreen
import com.don.hustletracker.ui.screens.task.AddtaskScreen
import com.don.hustletracker.ui.screens.task.TaskScreen
import com.don.hustletracker.ui.screens.welcomscreens.SplashScreen
import com.don.hustletracker.ui.screens.welcomscreens.WelcomeScreen
import com.don.hustletracker.ui.screens.welcomscreens.WelcomeScreen2
import com.don.hustletracker.ui.screens.business.*
import com.don.hustletracker.viewmodel.*

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_EARNING,
    earningViewModel: EarningViewModel
) {
    val context = LocalContext.current

    // TASK ViewModel setup
    val taskDao = AppDatabase.getDatabase(context).taskDao()
    val taskRepository = TaskRepository(taskDao)
    val taskViewModel: TaskViewModel = viewModel(factory = TaskViewModelFactory(taskRepository))

    // AUTH ViewModel setup
    val userDao = UserDatabase.getDatabase(context).userDao()
    val userRepository = UserRepository(userDao)
    val authViewModel = AuthViewModel(userRepository)

    // BUSINESS LOG ViewModel setup
    val businessLogDao = BusinessLogDatabase.getDatabase(context).businessLogDao()
    val businessLogRepository = BusinessLogRepository(businessLogDao)
    val businessLogFactory = BusinessLogViewModelFactory(businessLogRepository)
    val businessLogViewModel: BusinessLogViewModel = viewModel(factory = businessLogFactory)


    // Dummy health data
    val dummyHealthData = listOf(
        Health(activity = "Jogging", durationMinutes = 30, date = "2025-05-11"),
        Health(activity = "Yoga", durationMinutes = 45, date = "2025-05-10")
    )

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) { HomeScreen(navController) }
        composable(ROUT_ABOUT) { AboutScreen(navController) }

        // Auth Screens
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) { popUpTo(ROUT_REGISTER) { inclusive = true } }
            }
        }
        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) { popUpTo(ROUT_LOGIN) { inclusive = true } }
            }
        }

        // Welcome Screens
        composable(ROUT_SPLASH) { SplashScreen(navController) }
        composable(ROUT_WELCOME) { WelcomeScreen(navController) }
        composable(ROUT_WELCOME2) { WelcomeScreen2(navController) }

        // Dashboard & Task
        composable(ROUT_DASHBOARD) { DashboardScreen(navController) }
        composable(ROUT_ADDTASK) { AddtaskScreen(navController, taskViewModel) }
        composable(ROUT_TASK) { TaskScreen(navController, taskViewModel) }

        // Earnings
        composable(ROUT_EARNING) { EarningScreen(navController) }
        composable(ROUT_ADDEARNING) { AddEarningScreen(navController) }

        // Health
        composable(ROUT_ADDHEALTH) { AddHealthScreen(navController) }
        composable(ROUT_HEALTH) { HealthScreen(navController, dummyHealthData) }

        // Business Log

        composable("add_business_log") {
            AddBusinessLogScreen(
                navController = navController,
                onSave = { log -> businessLogViewModel.insertBusinessLog(log) }
            )
        }
        composable("view_business_log/{logId}") {
            ViewBusinessLogScreen(navController)
        }
        composable("edit_business_log/{logId}") {
            EditBusinessLogScreen(navController)
        }
        composable("business_log_list") {
            BusinessLogListScreen(
                navController = navController,
                logs = businessLogViewModel.businessLogs.collectAsState().value,
                onLogClick = { log ->
                    navController.navigate("view_business_log/${log.id}")
                }
            )
        }

    }
}
