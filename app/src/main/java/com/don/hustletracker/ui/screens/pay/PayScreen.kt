package com.don.hustletracker.ui.screens.pay

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.don.hustletracker.ui.theme.gold
import com.don.hustletracker.ui.theme.jetblack
import com.don.hustletracker.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayScreen(navController: NavController) {
    val mContext = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Smart Actions") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = white,
                    navigationIconContentColor = white,
                    actionIconContentColor = white
                )
            )
        },
        containerColor = jetblack
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxSize()
                .background(jetblack),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Quick Actions",
                color = white,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )

            ActionButton(
                icon = Icons.Default.ShoppingCart,
                label = "Open Mpesa",
                onClick = {
                    val intent = mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                    intent?.let { mContext.startActivity(it) }
                }
            )

            ActionButton(
                icon = Icons.Default.Call,
                label = "Make a Call",
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = "tel:0720245837".toUri()
                    mContext.startActivity(intent)
                }
            )

            ActionButton(
                icon = Icons.Default.Sms,
                label = "Send SMS",
                onClick = {
                    val smsIntent = Intent(Intent.ACTION_SENDTO).apply {
                        data = "smsto:0720245837".toUri()
                        putExtra("sms_body", "Hello....?")
                    }
                    mContext.startActivity(smsIntent)
                }
            )

            ActionButton(
                icon = Icons.Default.Share,
                label = "Share App",
                onClick = {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, "Check out this cool content!")
                    }
                    mContext.startActivity(Intent.createChooser(intent, "Share"))
                }
            )

            Text(
                text = "Sharing helps others discover useful tools — be someone’s plug!",
                color = Color.LightGray,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )

            ActionButton(
                icon = Icons.Default.Email,
                label = "Send Email",
                onClick = {
                    val emailIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_EMAIL, arrayOf("akinyiglory2@gmail.com"))
                        putExtra(Intent.EXTRA_SUBJECT, "Subject")
                        putExtra(Intent.EXTRA_TEXT, "Hello, this is the email body.")
                    }
                    mContext.startActivity(emailIntent)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Great working with you!\nCome back again anytime.",
                color = white,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ActionButton(icon: ImageVector, label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = gold),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = white)
            Spacer(modifier = Modifier.width(10.dp))
            Text(label, color = white)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PayScreenPreview() {
    PayScreen(navController = rememberNavController())
}
