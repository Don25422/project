package com.don.hustletracker.ui.screens.welcomscreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.don.hustletracker.R
import com.don.hustletracker.navigation.ROUT_LOGIN
import com.don.hustletracker.navigation.ROUT_WELCOME2
import com.don.hustletracker.ui.theme.Deepblue
import com.don.hustletracker.ui.theme.coolgrey
import com.don.hustletracker.ui.theme.jetblack
import com.don.hustletracker.ui.theme.white

@Composable
fun WelcomeScreen2(navController: NavController){
    Column (modifier = Modifier.fillMaxSize().background(jetblack),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text(text="Reach Your",
            color = white,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 40.sp,
        )
        Text(text="Full Potential",
            color = white,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 40.sp,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text="Build discipline and achieve",
            color = coolgrey,
            fontSize = 30.sp,
        )
        Text(text="growth in business, training,",
            color = coolgrey,
            fontSize = 30.sp,
        )
        Text(text="fitness, learning, and more.",
            color = coolgrey,
            fontSize = 30.sp,
        )

        Image(
            painter = painterResource(R.drawable.man),
            contentDescription = "home",
        )


        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = {
            navController.navigate(ROUT_LOGIN)

        },
            colors = ButtonDefaults.buttonColors(jetblack),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth().padding(start =20.dp, end = 20.dp).height(50.dp),
            border = BorderStroke(width = 2.dp, color = Deepblue)

        ) {
            Text( text = "NEXT",
                fontSize = 30.sp,
                color = white
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun WelcomeScreen2Preview(){
    WelcomeScreen2(rememberNavController())
}