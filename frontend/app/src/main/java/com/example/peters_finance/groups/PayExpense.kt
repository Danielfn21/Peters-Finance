package com.example.peters_finance.groups

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun PayExpensePage() {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar("Pay expense")
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    pageTitle: String
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Gray
                ),
                title = {
                    Text(pageTitle)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            //TODO: nav back
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
                .paddingFromBaseline(top = 130.dp)
                .padding(horizontal = 40.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            ExpenseInfo()

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseInfo() {
    val titleFontSize = 40.sp
    val textFontSize = 14.sp
    val textPadding = 10.dp

    Text("GROUP NAME", fontSize = titleFontSize, modifier = Modifier.padding(textPadding))
    Text("Total amount shared:", fontSize = textFontSize, modifier = Modifier.padding(textPadding))
    Text("AMOUNT", fontSize = textFontSize, modifier = Modifier.padding(textPadding))

    Button(
        onClick = { println("discard") },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray,
            contentColor = Color.Black
        )
    ) {
        Text("Discard Changes", fontSize = textFontSize)
    }


    Spacer(modifier = Modifier.size(220.dp))

    var paymentMessage by remember { mutableStateOf("Get scammed fool") }

    TextField(
        value = paymentMessage,
        onValueChange = { paymentMessage = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    )

    Button(
        onClick = { println("discard") },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 20.dp)
    ) {
        //TODO: Change this to a slider if excess time is somehow found
        Text("Pay your share", fontSize = 35.sp)
    }

}
