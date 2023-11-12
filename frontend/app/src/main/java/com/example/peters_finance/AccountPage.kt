package com.example.peters_finance

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*TODO: Move this into a appropriate folder later,
   More importantly resize everything so it actually fits
   on a normal sized phone, apparently fillMaxSize() is too tall
   */

@Preview
@Composable
fun AccountPage(){


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(0.dp, 30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,

        ){


        Card (
            border = BorderStroke(2.dp,Color.Black),
            modifier = Modifier.size(300.dp)
        ){
            Image(painter = painterResource(
                id = R.drawable.placeholder_user),
                contentDescription = "LTG",
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.size(15.dp))

        AccountInfo()

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountInfo(){

    //styling
    val infoFontSize = 16.sp
    val spacing = 8.dp
    val outlineTextFieldHeight = 40.dp //not in use yet as it breaks the fields

    //TODO Make it fetch these two values from the actual user
    val accountNameLabel = "LTG"
    val phoneNumberLabel = "123-call-kys-hotline"

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

    Column (
        horizontalAlignment = Alignment.Start,
    ){
        Text(text = "Change account name:", fontSize = infoFontSize)
        OutlinedTextField(value = newAccountName, onValueChange = {}, label = {Text(accountNameLabel)},)

        Spacer(modifier = Modifier.size(spacing))

        Text(text = "Change phone number:", fontSize = infoFontSize)
        OutlinedTextField(value = newPhoneNumber, onValueChange = {}, label = {Text(phoneNumberLabel)})

        Spacer(modifier = Modifier.size(spacing))

        Text(text = "Change password:", fontSize = infoFontSize)
        OutlinedTextField(value = newPassword, onValueChange = {}, label = {Text(passwordLabel)})

        Spacer(modifier = Modifier.size(spacing))

        //TODO: Make it check if its identical to newPassword
        Text(text = "Repeat new password:", fontSize = infoFontSize)
        OutlinedTextField(value = "", onValueChange = {}, label = {Text(passwordLabel)})

        Spacer(modifier = Modifier.size(spacing))
    }

    var checked by remember {
        mutableStateOf(true)
    }
    Row (
        modifier = Modifier
            .width(280.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = "Notifications:", fontSize = infoFontSize)

        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
            }
        )
    }

    Row (
        modifier = Modifier
            .width(280.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDC143C),
                contentColor = Color.Black
            )
        )  {
            Text(text = "Discard\nchanges", fontSize = infoFontSize)
        }

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(	0xFF228B22),
                contentColor = Color.Black
            )
        ) {
            Text(text = "Save\nchanges", fontSize = infoFontSize)
        }
    }
}