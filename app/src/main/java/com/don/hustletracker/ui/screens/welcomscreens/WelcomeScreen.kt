package com.don.hustletracker.ui.screens.welcomscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.don.hustletracker.R
import com.don.hustletracker.navigation.ROUT_WELCOME
import com.don.hustletracker.navigation.ROUT_WELCOME2
import com.don.hustletracker.ui.theme.Deepblue
import com.don.hustletracker.ui.theme.coolgrey
import com.don.hustletracker.ui.theme.jetblack
import com.don.hustletracker.ui.theme.white


@Composable
fun WelcomeScreen(navController: NavController){
    Column (modifier = Modifier.fillMaxSize().background(jetblack),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){

            Row {
                Image(
                    painter = painterResource(R.drawable.arrow),
                    contentDescription = "home",
                    modifier = Modifier.height(100.dp).width(100.dp).padding(bottom =10.dp)
                )

               Column {
                   Text(text="HUSTLE",
                       color = white,
                       fontWeight = FontWeight.ExtraBold,
                       fontSize = 36.sp,
                       fontFamily = FontFamily.Serif,
                   )
                   Spacer(modifier = Modifier.height(0.5.dp))

                   Text(text="TRACKER",
                       color = white,
                       fontWeight = FontWeight.ExtraBold,
                       fontSize = 36.sp,
                       fontFamily = FontFamily.Serif,
                   )

               }
            }

        Spacer(modifier = Modifier.height(100.dp))

        Text(text="Welcome",
            color = white,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 60.sp,
            )
        Spacer(modifier = Modifier.height(30.dp))

        Text(text="Track your daily progress",
            color = coolgrey,
            fontSize = 30.sp,
            )
        Text(text="and stay on top of your",
            color = coolgrey,
            fontSize = 30.sp,
            )
        Text(text="goals",
            color = coolgrey,
            fontSize = 30.sp,
            )

        Spacer(modifier = Modifier.height(250.dp))



        Button(onClick = {
            navController.navigate(ROUT_WELCOME2)

        },
            colors = ButtonDefaults.buttonColors(Deepblue),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().padding(start =20.dp, end = 20.dp).height(50.dp)

        ) {
            Text( text = "Get started",
                fontSize = 20.sp,
                )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview(){
    WelcomeScreen(rememberNavController())
}