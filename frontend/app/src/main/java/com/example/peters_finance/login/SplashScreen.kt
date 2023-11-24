package com.example.peters_finance.login

import StyledButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import com.example.peters_finance.ui.theme.Theme


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
    val spacing = 10.dp
    Theme {

        StyledButton(text = "LOGIN", onClick = { navController.navigate("LoginPage") })

        Spacer(modifier = Modifier.size(spacing))

        Text("OR", fontSize = 32.sp)

        Spacer(modifier = Modifier.size(spacing))

        StyledButton(
            text = "CREATE ACCOUNT",
            onClick = { navController.navigate("CreateAccountPage") })
    }


}
