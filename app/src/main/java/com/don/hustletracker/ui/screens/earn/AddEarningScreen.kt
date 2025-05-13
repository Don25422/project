package com.don.hustletracker.ui.screens.earn

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.don.hustletracker.model.Earning
import com.don.hustletracker.navigation.ROUT_EARNING
import com.don.hustletracker.ui.theme.jetblack
import com.don.hustletracker.viewmodel.EarningViewModel

@Composable
fun AddEarningScreen(
    navController: NavController? = null,
    viewModel: EarningViewModel? = null
) {
    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(jetblack)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Add Earning", style = MaterialTheme.typography.headlineSmall, color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                cursorColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                cursorColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val trimmedDesc = description.trim()
                val amountDouble = amount.toDoubleOrNull()

                Log.d("EarningDebug", "Desc: '$trimmedDesc', Amount: '$amount', ViewModel null: ${viewModel == null}")

                if (trimmedDesc.isNotEmpty() && amountDouble != null && viewModel != null) {
                    viewModel.insertEarning(Earning(description = trimmedDesc, amount = amountDouble))
                    navController?.navigate(ROUT_EARNING) {
                        popUpTo(ROUT_EARNING) { inclusive = true }
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Please enter a valid description and amount",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700))
        ) {
            Text("Save", color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddEarningScreenPreview() {
    AddEarningScreen()
}
