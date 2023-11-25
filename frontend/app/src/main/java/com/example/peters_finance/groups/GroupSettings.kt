package com.example.peters_finance.groups

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun GroupSettings() {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar("the zaza cookout xtreme edition") //TODO: Fetch this from current group
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
                .paddingFromBaseline(top = 88.dp)
                .padding(horizontal = 40.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GroupInfo()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupInfo() {
    val textPadding = 10.dp
    val textFontSize = 14.sp

    //Fetch these values from current group
    var groupName by remember { mutableStateOf("the zaza cookout xtreme edition") }
    var groupDescription by remember { mutableStateOf("BROTHER WE COOKING HELL YEAH BROTHER WE COOKING OOOOH YEAH BABY WITH JET FUEL CHINESE GATE TO HELL FIRE STOVE STYLE NAAN BREAD CEMENT WALL TYPE BEAT") }


    Text(
        text = "Change group name:",
        fontSize = textFontSize,
        modifier = Modifier.padding(textPadding)
    )
    TextField(
        value = groupName,
        onValueChange = { groupName = it },
        modifier = Modifier.fillMaxWidth()
    )

    Text(
        text = "Change description:",
        fontSize = textFontSize,
        modifier = Modifier.padding(textPadding)
    )
    TextField(
        value = groupDescription,
        onValueChange = { groupDescription = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    )

    Text(
        text = "Members:",
        fontSize = textFontSize,
        modifier = Modifier.padding(textPadding)
    )
    Card(
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){

            MemberEntry()

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF228B22),
                    contentColor = Color.Black
                )
            ) {
                Text("Add more", fontSize = textFontSize)
            }
        }

    }

    Spacer(modifier = Modifier.size(100.dp))

    Button(
        onClick = { println("save") },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray,
            contentColor = Color.Black,

            )
    ) {
        Text("Save Changes", fontSize = textFontSize)
    }

    Text("OR?", fontSize = textFontSize, modifier = Modifier.padding(textPadding))

    Button(
        onClick = { println("discard") },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray,
            contentColor = Color.Black
        )
    ) {
        Text("Discard Changes", fontSize = textFontSize)
    }

}

@Composable
fun MemberEntry(){
    //TODO: Generator that adds 'User's to to this
    Text("Bob Johnson")
}