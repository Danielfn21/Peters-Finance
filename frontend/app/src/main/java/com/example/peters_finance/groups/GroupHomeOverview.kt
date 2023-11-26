package com.example.peters_finance.groups

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.peters_finance.models.User


@Composable
fun GroupHomeOverview(
    navController: NavController,
    user: User?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "${user?.username}", Modifier.clickable {
                navController.navigate("AccountPage")
            })
            Spacer(modifier = Modifier.weight(1f))
            Popup()
        }

    }
}


@Composable
fun Groups(navController: NavController) {
    //TODO: Figure out how we wanna do data for the app
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Popup() {
    var popupControl by remember { mutableStateOf(false) }

    Button(
        onClick = { popupControl = true },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
    /*TODO: Issue here with dialog not dimming
       background on the emulator, if a physical
       device is used it will work as expected with background dimming
       / grey out*/
    if (popupControl) {

        Dialog(onDismissRequest = { popupControl = false }) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),

                ) {
                Text(text = "Group name")
                OutlinedTextField(value = "", onValueChange = {})
                Text(text = "Group Description")
                OutlinedTextField(value = "", onValueChange = {})
            }

        }
    }
}
































