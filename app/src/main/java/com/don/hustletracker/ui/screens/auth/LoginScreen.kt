package com.don.hustletracker.ui.screens.auth

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.don.hustletracker.R
import com.don.hustletracker.navigation.ROUT_DASHBOARD
import com.don.hustletracker.navigation.ROUT_ITEM


import com.don.hustletracker.navigation.ROUT_REGISTER
import com.don.hustletracker.ui.theme.focused
import com.don.hustletracker.ui.theme.jetblack
import com.don.hustletracker.ui.theme.login
import com.don.hustletracker.ui.theme.unfocused
import com.don.hustletracker.ui.theme.up
import com.don.hustletracker.ui.theme.white
import com.don.hustletracker.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Observe login logic
    LaunchedEffect(authViewModel) {
        authViewModel.loggedInUser = { user ->
            if (user == null) {
                Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            } else {
                if (user.role == "admin") {
                    navController.navigate(ROUT_ITEM) {
                    }
                } else {
                    navController.navigate(ROUT_DASHBOARD) {
                    }
                }
            }
        }
    }
//End of login logic

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(jetblack),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        // Animated Welcome Text
        Row ( modifier = Modifier.padding(top = 100.dp)){
            Image(
                painter = painterResource(R.drawable.arrow),
                contentDescription = "home",
                modifier = Modifier.height(100.dp).width(100.dp).padding(bottom = 10.dp)
            )

            Column {
                Text(text="HUSTLE",
                    color = white,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 46.sp,
                    fontFamily = FontFamily.Serif,
                )
                Spacer(modifier = Modifier.height(0.5.dp))

                Text(text="TRACKER",
                    color = white,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 46.sp,
                    fontFamily = FontFamily.Serif,
                )

            }
        }



        Spacer(modifier = Modifier.height(24.dp))

        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(login)


        ){
            Column(
                modifier = Modifier.padding(24.dp),

                ){
                Text(
                    text = "Login",
                    fontWeight = FontWeight.Bold,
                    color = white,

                )

                // Email Input
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email",
                        color = white,
                    )
                            },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(color = white)

                )

                Spacer(modifier = Modifier.height(12.dp))

                // Password Input with Show/Hide Toggle
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password",
                        color = white,
                        ) },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    trailingIcon = {
                        val image = if (passwordVisible) painterResource(R.drawable.img_5) else painterResource(R.drawable.img_6)
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(image, contentDescription = if (passwordVisible) "Hide Password" else "Show Password")
                        }
                    },
                    textStyle = TextStyle(color = white)
                )

                Spacer(modifier = Modifier.height(10.dp).padding(start = 900.dp))
                TextButton(onClick = { navController.navigate(ROUT_REGISTER) }) {
                    Text("Forgot password?",
                        color = up
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                // Gradient Login Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFFFFD700), Color(0xFFFFD700))
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            if (email.isBlank() || password.isBlank()) {
                                Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
                            } else {
                                authViewModel.loginUser(email, password)
                            }
                        },
                        modifier = Modifier.fillMaxSize(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Login", color = Color.White)
                    }
                }

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Register Navigation Button
        TextButton(onClick = { navController.navigate(ROUT_REGISTER) }) {
            Text("Don't have an account?",
                color = white
                )
            Spacer(modifier = Modifier.width(1.dp))
            Text("Sign up",
                color = up
                )
        }
    }
}