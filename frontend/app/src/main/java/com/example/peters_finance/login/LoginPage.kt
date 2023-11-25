package com.example.peters_finance.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.peters_finance.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)
        )
        Information(navController)
    }
}

@ExperimentalMaterial3Api
@Composable
fun Information(navController: NavController) {
    val loginFontSize = 17.sp
    val textFontSize = 14.sp
    val spacing = 10.dp

    //Inputs
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Text(text = "LOGIN", fontSize = 25.sp)
    // TODO: Make text field save data, for use when viewing account page
    OutlinedTextField(
        value = username,
        onValueChange = { usernameInput -> username = usernameInput },
        label = { Text("Account Name") }
    )
    OutlinedTextField(
        value = password,
        onValueChange = { passwordInput -> password = passwordInput },
        label = { Text("Password") },
        visualTransformation = PasswordVisualTransformation()
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(50.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Button(
            onClick = { navController.navigate("groupHomeOverview") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.Black,

                )
        ) {
            Text("LOGIN", fontSize = loginFontSize)
        }

        Spacer(modifier = Modifier.size(spacing))

        Text("Don't have an account?", fontSize = textFontSize)

        Spacer(modifier = Modifier.size(spacing))

        Button(
            onClick = { navController.navigate("CreateAccountPage") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.Black
            )
        ) {
            Text("CREATE ACCOUNT", fontSize = loginFontSize)
        }
    }

}