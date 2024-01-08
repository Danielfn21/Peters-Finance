package com.example.peters_finance.groups

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.peters_finance.models.Group
import com.example.peters_finance.models.NotificationGenerator
import com.example.peters_finance.models.User

@Composable
fun GroupExpensesOverviewPage(
    navController: NavController,
    user: User?,
    group: Group?
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(navController, user, group)
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
                            navController.popBackStack()
                        }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate("AddExpensePage")
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Add, tint = Color.Black, contentDescription = null)
                    }
                }
            )
        },

        ) {
        Column(
            modifier = Modifier
                .paddingFromBaseline(top = 80.dp)
                .padding(15.dp)
        ) {


            GroupExpenses(group)
            NotificationButton()
        }
    }
}

@Composable
private fun GroupExpenses(
    group: Group?
) {
    //TODO: Generator for expenses tabs, fetch these from group
}

@Preview
@Composable
private fun NotificationButton(){
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.size(60.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.Black
            ),
            onClick = {
                val notification = NotificationGenerator(
                    context,
                    "PAY YOUR BILLS",
                    "I AM BROKE AND I NEED MONEY"
                )
                notification.generateNotification()
            }) {
            Text("Notify group members")
        }
    }
}

