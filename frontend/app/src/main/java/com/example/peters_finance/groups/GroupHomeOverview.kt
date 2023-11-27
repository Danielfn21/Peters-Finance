package com.example.peters_finance.groups

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.peters_finance.R
import com.example.peters_finance.models.Group
import com.example.peters_finance.models.User


@Composable
fun GroupHomeOverview(
    navController: NavController,
    user: User?,
    setGroup: (Group?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    navController.navigate("AccountPage")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.placeholder_user
                    ),
                    contentDescription = "LTG",
                    modifier = Modifier.size(22.dp)
                )

                Text(
                    text = " ${user?.username}",
                    fontSize = 22.sp,
                    modifier = Modifier.clickable {
                        navController.navigate("AccountPage")
                    })
            }

            Spacer(modifier = Modifier.weight(1f))
            PopUp(user, navController)
        }

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            user?.groups?.forEach { group ->
                GroupDisplayer(navController, group, setGroup)
            }
        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupDisplayer(
    navController: NavController,
    group: Group?,
    setGroup: (Group?) -> Unit
) {

    val textPadding = 5.dp

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            setGroup(group)
            navController.navigate("GroupChat")
        }
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
        ) {
            Row(horizontalArrangement = Arrangement.Start) {
                Text(
                    "${group?.name}",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(textPadding)
                )
            }
            Row(horizontalArrangement = Arrangement.Start) {
                Text(
                    "${group?.description}",
                    modifier = Modifier.padding(textPadding)
                )
            }
            Row(horizontalArrangement = Arrangement.Start) {
                Text(
                    "Member count: ${group?.members?.size}",
                    modifier = Modifier.padding(textPadding)
                )
            }
        }
    }
}

fun groupGenerator(
    name: String,
    description: String,
    user: User?
) {
    //Checks if any fields are empty
    if (name.isEmpty() || description.isEmpty()) return

    var newGroup = Group(name, description, mutableListOf())

    user?.let {
        //Adds the logged in user to the newly created group
        newGroup.members = mutableListOf(it)

        //Adds the newly created group to the logged in user
        it.groups = (it.groups ?: emptyList()).toMutableList().apply {
            add(newGroup)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopUp(
    user: User?,
    navController: NavController
) {
    var popupControl by remember { mutableStateOf(false) }

    Button(
        onClick = { popupControl = true },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            modifier = Modifier.size(22.dp)
        )
    }
    /*TODO: Issue here with dialog not dimming
       background on the emulator, if a physical
       device is used it will work as expected with background dimming
       / grey out*/
    if (popupControl) {

        Dialog(onDismissRequest = { popupControl = false }) {

            val maxGroupNameChars = 25

            var groupName by remember { mutableStateOf("") }
            var groupDescription by remember { mutableStateOf("") }

            val cardPadding = 10.dp

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(16.dp),
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                        .padding(15.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {

                    Text(text = "Group name", modifier = Modifier.padding(cardPadding))
                    OutlinedTextField(
                        value = groupName,
                        onValueChange = {  if (it.length <= maxGroupNameChars) groupName = it },
                    )

                    Text(text = "Group Description", modifier = Modifier.padding(cardPadding))
                    OutlinedTextField(
                        value = groupDescription,
                        onValueChange = { groupDescriptionInput ->
                            groupDescription = groupDescriptionInput
                        },
                        modifier = Modifier.height(140.dp)
                    )

                    Button(
                        onClick = {
                            popupControl = false
                            groupGenerator(groupName, groupDescription, user)
                            //This is dumb, but it forces the recomposition of the page
                            navController.navigate("GroupHomeOverview")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.Black
                        ),
                        modifier = Modifier.padding(cardPadding)
                    ) {
                        Text("Create Group")
                    }
                }
            }
        }
    }
}



