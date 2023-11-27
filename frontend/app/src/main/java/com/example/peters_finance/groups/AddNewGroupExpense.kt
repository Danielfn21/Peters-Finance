package com.example.peters_finance.groups

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.peters_finance.models.Group
import com.example.peters_finance.models.User

@Composable
fun AddNewGroupExpensePage(
    navController: NavController,
    user: User?,
    group: Group?
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(navController, user, group) //TODO: Fetch this from current group
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    navController: NavController,
    user: User?,
    group: Group?
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Gray
                ),
                title = {
                    if (group != null) {
                        Text(group.name)
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate("ViewExpensePage")
                        }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
            )
        },

        ) {
        Column(
            modifier = Modifier
                .paddingFromBaseline(top = 88.dp)
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.size(100.dp))

            AddNewGroupExpense(navController, user, group)

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddNewGroupExpense(
    navController: NavController,
    user: User?,
    group: Group?
) {
    val textPadding = 10.dp
    val textFontSize = 18.sp

    val maxAmountDigits = 9

    //Fetch these values from current group
    var amount by remember { mutableStateOf("0") }
    var groupDescription by remember { mutableStateOf("") }


    Text(
        text = "Total Amount:",
        fontSize = textFontSize,
        modifier = Modifier.padding(textPadding)
    )
    TextField(
        value = amount,
        onValueChange = {
            if (it.isDigitsOnly() && it.length <= maxAmountDigits) amount = it
        },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )

    Text(
        text = "Description:",
        fontSize = textFontSize,
        modifier = Modifier.padding(textPadding)
    )
    TextField(
        value = groupDescription,
        onValueChange = { groupDescription = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    )

    Spacer(modifier = Modifier.size(20.dp))

    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray,
            contentColor = Color.Black
        )
    ) {
        Text("Share evenly", fontSize = textFontSize)
    }

    Spacer(modifier = Modifier.size(30.dp))

    Box(
        modifier = Modifier
            .width(340.dp)
            .border(BorderStroke(2.dp, Color.Black)),
    ) {


        Column(
            modifier = Modifier
                .width(340.dp)
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Payers(user, group)

        }
    }

    Spacer(modifier = Modifier.size(60.dp))

    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray,
            contentColor = Color.Black
        )
    ) {
        Text("Add Expense", fontSize = textFontSize)
    }
}

@Composable
private fun Payers(
    user: User?,
    group: Group?
) {

}
