package com.example.peters_finance.groups

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.peters_finance.models.Group
import com.example.peters_finance.models.User


@Composable
fun GroupChat(
    navController: NavController,
    currentUser: User?, //will be used in chat
    group: Group?
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        TopBar(navController, group)

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    navController: NavController,
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
                            navController.navigate("GroupHomeOverview")
                        }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate("GroupSettings")
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = null)
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
            Chat()
        }
    }
}

@Composable
fun Chat() {
    Text(text = "EPIC CHATTING CURRENTLY ONGOING")
    //TODO: Do as below, dummy chat
    //https://medium.com/@meytataliti/building-a-simple-chat-app-with-jetpack-compose-883a240592d4
}