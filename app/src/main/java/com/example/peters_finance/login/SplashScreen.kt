package com.example.peters_finance.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.peters_finance.R


@Composable
fun SplashScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(330.dp)
        )

        SplashOptions(navController)
    }
}


@Composable
fun SplashOptions(
    navController: NavController
) {
    val splashFontSize = 32.sp
    val spacing = 10.dp


    Button(
        onClick = { navController.navigate("LoginPage") },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray,
            contentColor = Color.Black
        )
    ) {
        Text("LOGIN", fontSize = splashFontSize)
    }

    Spacer(modifier = Modifier.size(spacing))

    Text("OR", fontSize = splashFontSize)

    Spacer(modifier = Modifier.size(spacing))

    Button(
        onClick = { navController.navigate("groupHomeOverview") },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray,
            contentColor = Color.Black
        )
    ) {
        Text("CREATE ACCOUNT", fontSize = splashFontSize)
    }
}
