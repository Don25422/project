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
import com.don.hustletracker.repository.TaskRepository
import com.don.hustletracker.repository.UserRepository
import com.don.hustletracker.ui.screens.about.AboutScreen
import com.don.hustletracker.ui.screens.auth.LoginScreen
import com.don.hustletracker.ui.screens.auth.RegisterScreen
import com.don.hustletracker.ui.screens.dashboard.DashboardScreen
import com.don.hustletracker.ui.screens.earn.EarningScreen
import com.don.hustletracker.ui.screens.home.HomeScreen
import com.don.hustletracker.ui.screens.task.AddtaskScreen
import com.don.hustletracker.ui.screens.task.TaskScreen
import com.don.hustletracker.ui.screens.welcomscreens.SplashScreen
import com.don.hustletracker.ui.screens.welcomscreens.WelcomeScreen
import com.don.hustletracker.ui.screens.welcomscreens.WelcomeScreen2
import com.don.hustletracker.viewmodel.AuthViewModel
import com.don.hustletracker.viewmodel.EarningViewModel
import com.don.hustletracker.viewmodel.TaskViewModel
import com.don.hustletracker.viewmodel.TaskViewModelFactory
import com.don.hustletracker.model.Health
import com.don.hustletracker.repository.BusinessLogRepository
import com.don.hustletracker.ui.screens.business.AddBusinessLogScreen
import com.don.hustletracker.ui.screens.business.BusinessLogListScreen
import com.don.hustletracker.ui.screens.business.EditBusinessLogScreen
import com.don.hustletracker.ui.screens.business.ViewBusinessLogScreen
import com.don.hustletracker.ui.screens.earn.AddEarningScreen
import com.don.hustletracker.ui.screens.health.AddHealthScreen
import com.don.hustletracker.ui.screens.health.HealthScreen
import com.don.hustletracker.viewmodel.BusinessLogViewModel


fun EditBusinessLogScreen(log: NavHostController) {


}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_EARNING,
    earningViewModel: EarningViewModel
) {

    val context = LocalContext.current
    val dao = AppDatabase.getDatabase(context).taskDao()
    val repository = TaskRepository(dao)
    val factory = TaskViewModelFactory(repository)

    val taskViewModel: TaskViewModel = viewModel(
        factory = factory
    )
    val dummyData = listOf(
        Health(activity = "Jogging", durationMinutes = 30, date = "2025-05-11"),
        Health(activity = "Yoga", durationMinutes = 45, date = "2025-05-10")
    )
    val database = BusinessLogDatabase.getDatabase(context)
    val BusinessLogRepository = BusinessLogRepository(database.businessLogDao())
    val businessLogViewModel = BusinessLogViewModel(repository)



    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }

        //AUTHENTICATION
        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }

        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUT_WELCOME) {
            WelcomeScreen(navController)
        }
        composable(ROUT_WELCOME2) {
            WelcomeScreen2(navController)
        }
        composable(ROUT_DASHBOARD) {
            DashboardScreen(navController)
        }

        composable(ROUT_ADDTASK) {
            AddtaskScreen(navController = navController, taskViewModel = taskViewModel)
        }
        composable(ROUT_TASK) {
            TaskScreen(navController = navController, taskViewModel = taskViewModel)
        }
        composable(ROUT_EARNING) {
            EarningScreen(navController = navController)
        }
        composable(ROUT_ADDEARNING) {
            AddEarningScreen(navController)
        }
        composable(ROUT_ADDHEALTH) {
            AddHealthScreen(navController)
        }
        composable(ROUT_HEALTH) {
            HealthScreen(navController = rememberNavController(), healthData = dummyData)
        }
        composable("business_log_list") {
            BusinessLogListScreen(navController, businessLogViewModel)
        }
        composable("add_business_log") {
            AddBusinessLogScreen(navController)
        }
        composable("view_business_log/{logId}") { backStackEntry ->
            val logId = backStackEntry.arguments?.getString("logId")?.toIntOrNull()
            logId?.let {
                ViewBusinessLogScreen(navController)
            }
        }
        composable("edit_business_log/{logId}") { backStackEntry ->
            val logId = backStackEntry.arguments?.getString("logId")?.toIntOrNull()
            logId?.let {
                EditBusinessLogScreen(navController)
            }














    }
}