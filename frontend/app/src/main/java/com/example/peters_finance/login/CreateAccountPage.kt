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
import com.example.peters_finance.models.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountPage(
    navController: NavController,
    newUser: (User?) -> Unit
) {
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
        AccountInformation(navController, newUser)
    }
}

fun createUser(
    phoneNumber: String,
    accountName: String,
    password: String,
    repeatPassword: String
): User? {
    //Checks if any fields are empty
    if (phoneNumber.isEmpty() || accountName.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
        return null
    }

    //Checks if passwords match
    if (password != repeatPassword) {
        return null
    }

    return User(accountName, phoneNumber, password);
}


@ExperimentalMaterial3Api
@Composable
fun AccountInformation(
    navController: NavController,
    newUser: (User?) -> Unit
) {
    val loginFontSize = 17.sp
    val textFontSize = 14.sp
    val spacing = 10.dp

    //Inputs
    var phoneNumber by remember { mutableStateOf("") }
    var accountName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    Text(text = "CREATE ACCOUNT", fontSize = 25.sp)
    // TODO: Make text field save data, for use when viewing account page
    OutlinedTextField(
        value = phoneNumber,
        onValueChange = { phoneNumberInput -> phoneNumber = phoneNumberInput },
        label = { Text("Phone Number") })
    OutlinedTextField(
        value = accountName,
        onValueChange = { accountNameInput -> accountName = accountNameInput },
        label = { Text("Account Name") })
    OutlinedTextField(
        value = password,
        onValueChange = { passwordInput -> password = passwordInput },
        label = { Text("Password") },
        visualTransformation = PasswordVisualTransformation()
    )
    OutlinedTextField(
        value = repeatPassword,
        onValueChange = { repeatPasswordInput -> repeatPassword = repeatPasswordInput },
        label = { Text("Repeat Password") },
        visualTransformation = PasswordVisualTransformation()
    )
    Button(
        onClick = {
            var user = createUser(phoneNumber, accountName, password, repeatPassword)
            if (user != null) {
                newUser(user)
                navController.navigate("GroupHomeOverview")
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray,
            contentColor = Color.Black
        ),
        modifier = Modifier.padding(15.dp)
    ) {
        Text("CREATE ACCOUNT", fontSize = loginFontSize)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(50.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Spacer(modifier = Modifier.size(spacing))

        Text("Already have an account?", fontSize = textFontSize)

        Spacer(modifier = Modifier.size(spacing))

        Button(
            onClick = { navController.navigate("LoginPage") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.Black
            )
        ) {
            Text("LOGIN", fontSize = loginFontSize)
        }
    }

}