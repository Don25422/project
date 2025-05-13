package com.don.hustletracker.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.don.hustletracker.data.AppDatabase
import com.don.hustletracker.data.HealthDatabase
import com.don.hustletracker.data.UserDatabase
import com.don.hustletracker.model.Health
import com.don.hustletracker.repository.HealthRepository
import com.don.hustletracker.repository.TaskRepository
import com.don.hustletracker.repository.UserRepository
import com.don.hustletracker.ui.screens.ProductScreen.AddProductScreen
import com.don.hustletracker.ui.screens.about.AboutScreen
import com.don.hustletracker.ui.screens.auth.LoginScreen
import com.don.hustletracker.ui.screens.auth.RegisterScreen
import com.don.hustletracker.ui.screens.dashboard.DashboardScreen
import com.don.hustletracker.ui.screens.earn.AddEarningScreen
import com.don.hustletracker.ui.screens.earning.EarningScreen
import com.don.hustletracker.ui.screens.health.AddHealthScreen
import com.don.hustletracker.ui.screens.health.HealthScreen
import com.don.hustletracker.ui.screens.home.HomeScreen
import com.don.hustletracker.ui.screens.pay.PayScreen
import com.don.hustletracker.ui.screens.request.EditProductScreen
import com.don.hustletracker.ui.screens.request.ProductListScreen
import com.don.hustletracker.ui.screens.task.AddtaskScreen
import com.don.hustletracker.ui.screens.task.TaskScreen
import com.don.hustletracker.ui.screens.welcomscreens.SplashScreen
import com.don.hustletracker.ui.screens.welcomscreens.WelcomeScreen
import com.don.hustletracker.ui.screens.welcomscreens.WelcomeScreen2
import com.don.hustletracker.viewmodel.*
import com.don.hustletracker.navigation.*



@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    earningViewModel: EarningViewModel,
    startDestination: String = ROUT_SPLASH,
    productViewModel: ProductViewModel = viewModel(),
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

    // HEALTH ViewModel setup
    val dao = HealthDatabase.getDatabase(context).healthDao()
    val repository = remember { HealthRepository(dao) }
    val healthViewModel: HealthViewModel = viewModel(factory = HealthViewModelFactory(repository))

    var healthList by remember { mutableStateOf(listOf<Health>()) }

    // Load once
    LaunchedEffect(Unit) {
        healthViewModel.loadHealth {
            healthList = it
        }
    }

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
        composable(ROUT_EARNING) {
            EarningScreen(viewModel = earningViewModel, navController = navController)
        }
        composable(ROUT_ADDEARNING) {
            AddEarningScreen(viewModel = earningViewModel, navController = navController)
        }

        // Business Log / Product Screens
        composable(ROUT_ADD_PRODUCT) {
            AddProductScreen(navController, productViewModel)
        }
        composable(ROUT_PRODUCT_LIST) {
            ProductListScreen(navController, productViewModel)
        }
        composable(
            route = ROUT_EDIT_PRODUCT,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            if (productId != null) {
                EditProductScreen(productId, navController, productViewModel)
            }
        }

        composable(ROUT_PAY) {
            PayScreen(navController)
        }

        // Health Screens
        composable(ROUT_HEALTH) {
            HealthScreen(
                navController = navController,
                healthData = healthList
            )
        }

        composable(ROUT_ADDHEALTH) {
            AddHealthScreen(navController = navController) { newHealth ->
                healthViewModel.insertHealth(newHealth) {
                    healthViewModel.loadHealth {
                        healthList = it
                        navController.navigate(ROUT_HEALTH)
                    }
                }
            }
        }
    }
}
