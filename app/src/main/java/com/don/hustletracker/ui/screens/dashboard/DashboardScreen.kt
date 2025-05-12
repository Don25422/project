package com.don.hustletracker.ui.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.don.hustletracker.R
import com.don.hustletracker.navigation.ROUT_EARNING
import com.don.hustletracker.navigation.ROUT_TASK
import com.don.hustletracker.ui.theme.Deepblue
import com.don.hustletracker.ui.theme.EmeraldGreen
import com.don.hustletracker.ui.theme.charcoalblue
import com.don.hustletracker.ui.theme.jetblack
import com.don.hustletracker.ui.theme.white

@Composable
fun DashboardScreen(navController: NavController){
    Column (modifier = Modifier.fillMaxSize().background(jetblack)){
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ){

            Image(
                painter = painterResource(R.drawable.arrow),
                contentDescription = "home",
                modifier = Modifier.size(80.dp).padding(start=20.dp, top = 10.dp)

            )


            Column (modifier = Modifier
                .fillMaxWidth()
                .background(jetblack)
                .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = "Hustle Tracker",
                    color = white,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold

                )
            }
        }

        Box(modifier = Modifier.height(4.dp).background(Deepblue).fillMaxWidth())

        Spacer(modifier = Modifier.height(10.dp))

        Column (horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.verticalScroll(rememberScrollState())
            ){
            Text(text = "Overview of Your Day",
                color = EmeraldGreen,
                fontSize = 25.sp,
                modifier = Modifier.padding(start = 0.dp)

            )
            Spacer(modifier = Modifier.height(10.dp))

            //card 1
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(100.dp)
                .clickable { navController.navigate(ROUT_TASK) },
                colors = CardDefaults.cardColors(charcoalblue)



            ) {
                Row {
                    Image(
                        painter = painterResource(R.drawable.img_3),
                        contentDescription = "home",
                        modifier = Modifier.padding(start = 20.dp, top = 30.dp)
                    )

                    Text(text = "Tasks completed",
                    color = white,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 20.dp, top = 30.dp)

                ) }

            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(100.dp)
                .clickable { navController.navigate(ROUT_EARNING) },
                colors = CardDefaults.cardColors(charcoalblue),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),



            ) {
                Row {
                    Image(
                        painter = painterResource(R.drawable.img_4),
                        contentDescription = "home",
                        modifier = Modifier.padding(start = 20.dp, top = 30.dp)
                    )
                    Column {
                        Text(
                            text = "Total Earnings",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )


                        val totalEarnings = null
                        Text(
                           text = "Ksh ${String.format("%,d", totalEarnings)}",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Today: Ksh 750",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }

                }

            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(100.dp),
                colors = CardDefaults.cardColors(charcoalblue)



            ) {
                Row {
                    Image(
                        painter = painterResource(R.drawable.img_9),
                        contentDescription = "home",
                        modifier = Modifier.padding(start = 20.dp, top = 30.dp)
                    )

                    Text(text = "Investment snapshot",
                        color = white,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(start = 20.dp, top = 30.dp)

                    )
                }

            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(100.dp),
                colors = CardDefaults.cardColors(charcoalblue)



            ) {
                Row {
                    Image(
                        painter = painterResource(R.drawable.img_8),
                        contentDescription = "home",
                        modifier = Modifier.padding(start = 20.dp, top = 30.dp)
                    )

                    Text(text = "Motivation quote of day",
                        color = white,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(start = 20.dp, top = 30.dp)

                    )
                }

            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(100.dp),
                colors = CardDefaults.cardColors(charcoalblue)



            ) {
                Row {
                    Image(
                        painter = painterResource(R.drawable.img_7),
                        contentDescription = "home",
                        modifier = Modifier.padding(start = 20.dp, top = 30.dp)
                    )

                    Text(text = "Health & Fitness",
                        color = white,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(start = 20.dp, top = 30.dp)

                    )
                }

            }


        }
    }

}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview(){
    DashboardScreen(rememberNavController())
}