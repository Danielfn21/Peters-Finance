package com.example.peters_finance.groups

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.peters_finance.models.Expense
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
            )
        },

        ) {

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .paddingFromBaseline(top = 88.dp)
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.size(40.dp))

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
    val textPadding = 6.dp
    val textFontSize = 18.sp

    val maxAmountDigits = 9
    val maxNameLength = 25

    //Fetch these values from current group
    var groupName by remember { mutableStateOf("") }
    var totalAmount by remember { mutableStateOf("0") }
    var groupDescription by remember { mutableStateOf("") }

    Text(
        text = "Name:",
        fontSize = textFontSize,
        modifier = Modifier.padding(textPadding)
    )
    OutlinedTextField(
        value = groupName,
        onValueChange = {
            if (it.length <= maxNameLength) groupName = it
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
    Text(
        text = "Total Amount:",
        fontSize = textFontSize,
        modifier = Modifier.padding(textPadding)
    )
    OutlinedTextField(
        value = totalAmount,
        onValueChange = {
            if (it.isDigitsOnly() && it.length <= maxAmountDigits) totalAmount = it
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
    OutlinedTextField(
        value = groupDescription,
        onValueChange = { groupDescription = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    )

    Spacer(modifier = Modifier.size(20.dp))

    var tempExpense: Expense? = null
    try {
        tempExpense = Expense(
            groupName,
            groupDescription,
            totalAmount.toInt(),
            group?.members?.toMutableList() ?: mutableListOf(),
            emptyMap<User, Number>().toMutableMap()
        )
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }


    Button(
        onClick = {
            val totalAmountInput = totalAmount.toInt()


            // Distribute the total amount evenly among payers
            val evenlyDistributedAmount: Number
            if (group?.members?.size!! <= 1 || totalAmountInput == 0) {
                evenlyDistributedAmount = 0
            } else {
                evenlyDistributedAmount = totalAmountInput / (group.members.size - 1)
            }

            val updatedPayers: MutableMap<User, Number>? = tempExpense?.split
            if (tempExpense != null && updatedPayers != null) {
                for (payer in tempExpense.payers) {
                    updatedPayers[payer] = evenlyDistributedAmount
                }
            }
            updatedPayers?.set(user!!, 0)

            // Update the Expense data class
            if (tempExpense != null) {
                tempExpense.name = groupName
                tempExpense.description = groupDescription
                tempExpense.amount = totalAmount.toInt()
                tempExpense.split = updatedPayers?.toMutableMap()!!
            }


        },
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

            Payers(user, group, tempExpense)

        }
    }

    Spacer(modifier = Modifier.size(10.dp))

    Button(
        onClick = { addExpense(group, tempExpense) },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray,
            contentColor = Color.Black
        )
    ) {
        Text("Add Expense", fontSize = textFontSize, modifier = Modifier.padding(textPadding))
    }
}

private fun addExpense(
    group: Group?,
    expense: Expense?
) {
    //TODO: Add new Expense to group
}


//This is broken somehow, I give up for now
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Payers(
    user: User?,
    group: Group?,
    tempExpense: Expense?,
) {

    val maxDigits = 5

    group?.members?.forEach { member ->

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val isCurrentUser = member == user
            val amountFromExpense = tempExpense?.split?.get(member)?.toString() ?: "0"
            var amount by remember { mutableStateOf(amountFromExpense) }

            Text(if (isCurrentUser) "You" else member.username, modifier = Modifier.padding(5.dp))
            OutlinedTextField(
                value = amount,
                onValueChange = {
                    if (it.isDigitsOnly() && it.length <= maxDigits) {
                        amount = it
                        // Update the tempExpense split map
                        tempExpense?.split?.put(member, it.toInt())
                    }
                },
                modifier = Modifier.width(100.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
            )
        }
    }
}
