package com.don.hustletracker.ui.screens.welcomscreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.don.hustletracker.ui.theme.jetblack
import com.don.hustletracker.R
import com.don.hustletracker.navigation.ROUT_WELCOME
import com.don.hustletracker.ui.theme.white
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController){

    //Navigation
    val coroutine = rememberCoroutineScope()
    coroutine.launch{
        delay(2000)
        navController.navigate(ROUT_WELCOME)

    }
    //End of navigation

    Column (
        modifier = Modifier
            .background(jetblack)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Image(
            painter = painterResource(R.drawable.arrow),
            contentDescription = "home",
            modifier = Modifier.size(250.dp)
        )

        Text(text="HUSTLE",
            color = white,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 70.sp,
            fontFamily = FontFamily.Serif,
        )
        Spacer(modifier = Modifier.height(0.5.dp))

        Text(text="TRACKER",
            color = white,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 70.sp,
            fontFamily = FontFamily.Serif,
        )
    }

}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(rememberNavController())
}