package com.example.peters_finance.groups

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.peters_finance.models.Group
import com.example.peters_finance.models.User


@Composable
fun GroupSettings(
    navController: NavController,
    allUsers: List<User>,
    group: Group?
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(navController, allUsers, group) //TODO: Fetch this from current group
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    navController: NavController,
    allUsers: List<User>,
    group: Group?,
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
                            navController.navigate("GroupChat")
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
            GroupInfo(group, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupInfo(
    group: Group?,
    navController: NavController
) {
    val textPadding = 6.dp
    val textFontSize = 14.sp

    val maxGroupNameChars = 25

    var newGroupName by remember { mutableStateOf("") }
    var newGroupDescription by remember { mutableStateOf("") }


    Text(
        text = "Change group name:",
        fontSize = textFontSize,
        modifier = Modifier.padding(textPadding)
    )
    OutlinedTextField(
        value = newGroupName,
        onValueChange = {
            if (it.length <= maxGroupNameChars) {
                newGroupName = it
            }
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        label = {
            if (group != null) {
                Text(group.name)
            }
        }
    )

    Text(
        text = "Change description:",
        fontSize = textFontSize,
        modifier = Modifier.padding(textPadding)
    )
    OutlinedTextField(
        value = newGroupDescription,
        onValueChange = { descriptionInput ->
            newGroupDescription = descriptionInput
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp),
        label = {
            if (group != null) {
                Text(group.description)
            }
        }
    )

    Spacer(modifier = Modifier.size(10.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDC143C),
                contentColor = Color.Black
            )
        ) {
            Text(text = "Discard\nchanges", fontSize = textFontSize)
        }

        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF228B22),
                contentColor = Color.Black
            )
        ) {
            Text(text = "Save\nchanges", fontSize = textFontSize)
        }
    }

    Spacer(modifier = Modifier.size(20.dp))

    MemberOverview(group, navController)

}

@Composable
fun MemberOverview(
    group: Group?,
    navController: NavController
) {
    val textPadding = 6.dp
    val textFontSize = 14.sp

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


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            for (user in group?.members!!) {
                MemberEntry(user, group, navController)
            }

            Button(
                onClick = { addUserToGroup() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF228B22),
                    contentColor = Color.Black
                )
            ) {
                Text("Add more", fontSize = textFontSize)
            }
        }

    }
}

fun addUserToGroup() {

}

@Composable
fun MemberEntry(
    user: User?,
    group: Group?,
    navController: NavController
) {
    var removalText by remember {
        mutableStateOf("Remove")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(Color.LightGray),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(user?.username.toString())

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                user?.groups?.remove(group)
                group?.members?.remove(user)
                removalText = "Removed"

                //This is dumb, but it forces the recomposition of the page
                navController.navigate("GroupSettings")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDC143C),
                contentColor = Color.Black
            )
        ) {
            Text(removalText, modifier = Modifier.padding(8.dp))
        }
    }
}