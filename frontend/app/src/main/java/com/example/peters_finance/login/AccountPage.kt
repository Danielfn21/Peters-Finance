package com.example.peters_finance.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.peters_finance.R
import com.example.peters_finance.models.User


@Composable
fun AccountPage(
    navController: NavController,
    currentUser: User?
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = {
                    navController.navigate("GroupHomeOverview")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }

        Card(
            modifier = Modifier
                .size(300.dp),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.placeholder_user
                ),
                contentDescription = "LTG",
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.size(15.dp))

        AccountInfo(currentUser, navController)

    }
}

fun saveChanges(
    currentUser: User?,
    newAccountName: String,
    newPhoneNumber: String,
    newPassword: String,
    repeatNewPassword: String
) {

    /*TODO: If someone has time: Make it so that it only updates the fields that are not empty,
    *  instead of forcing changes in all fields. */
    //Checks different cases
    if (newAccountName.isEmpty() || newPhoneNumber.isEmpty() || newPassword.isEmpty() || repeatNewPassword.isEmpty()) {
        return
    }

    //Checks if passwords match
    if (newPassword != repeatNewPassword) {
        return
    }

    //Update user info
    currentUser?.username = newAccountName
    currentUser?.phone_number = newPhoneNumber
    currentUser?.password = newPassword
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountInfo(
    currentUser: User?,
    navController: NavController
) {

    //styling
    val infoFontSize = 16.sp
    val spacing = 8.dp

    //TODO Make it fetch these two values from the actual user
    val accountNameLabel = currentUser?.username
    val phoneNumberLabel = currentUser?.phone_number

    val passwordLabel = "*************"

    //New values
    var newAccountName by remember {
        mutableStateOf("")
    }
    var newPhoneNumber by remember {
        mutableStateOf("")
    }
    var newPassword by remember {
        mutableStateOf("")
    }
    var repeatNewPassword by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(text = "Change account name:", fontSize = infoFontSize)
        OutlinedTextField(
            value = newAccountName,
            onValueChange = { newAccountNameInput ->
                newAccountName = newAccountNameInput
            },
            label = { Text(accountNameLabel.toString()) }
        )

        Spacer(modifier = Modifier.size(spacing))

        Text(text = "Change phone number:", fontSize = infoFontSize)
        OutlinedTextField(
            value = newPhoneNumber,
            onValueChange = { newPhoneNumberInput ->
                newPhoneNumber = newPhoneNumberInput
            },
            label = { Text(phoneNumberLabel.toString()) }
        )

        Spacer(modifier = Modifier.size(spacing))

        Text(text = "Change password:", fontSize = infoFontSize)
        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPasswordInput ->
                newPassword = newPasswordInput
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.clickable { repeatNewPassword = "" },
            label = { Text(passwordLabel) }
        )

        Spacer(modifier = Modifier.size(spacing))

        Text(text = "Repeat new password:", fontSize = infoFontSize)
        OutlinedTextField(
            value = repeatNewPassword,
            onValueChange = { repeatNewPasswordInput ->
                repeatNewPassword = repeatNewPasswordInput
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.clickable { repeatNewPassword = "" },
            label = { Text(passwordLabel) }
        )

        Spacer(modifier = Modifier.size(spacing))
    }

    var checked by remember {
        mutableStateOf(true)
    }
    Row(
        modifier = Modifier
            .width(280.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Notifications:", fontSize = infoFontSize)

        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
            }
        )
    }
    Row(
        modifier = Modifier
            .width(280.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Dark mode:", fontSize = infoFontSize)

        Switch(
            checked = false,
            onCheckedChange = {
                checked = it
            }
        )
    }

    Row(
        modifier = Modifier
            .width(280.dp)
            .padding(bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {
                //This is dumb, but it forces the recomposition of the page
                navController.navigate("AccountPage")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDC143C),
                contentColor = Color.Black
            )
        ) {
            Text(text = "Discard\nchanges", fontSize = infoFontSize)
        }

        Button(
            onClick = {
                saveChanges(
                    currentUser,
                    newAccountName,
                    newPhoneNumber,
                    newPassword,
                    repeatNewPassword
                )
                //This is dumb, but it forces the recomposition of the page
                navController.navigate("AccountPage")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF228B22),
                contentColor = Color.Black
            )
        ) {
            Text(text = "Save\nchanges", fontSize = infoFontSize)
        }
    }
}