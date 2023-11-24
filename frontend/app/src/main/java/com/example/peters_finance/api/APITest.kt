package com.example.peters_finance.api

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.peters_finance.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//THIS SHOULD WORK BUT ANDROID IS REALLY FUCKING STUPID
//IT DOES NOT WANT TO FETCH FROM SHIT THAT IS NOT HTTPS Y(UIBSAYHODG=(/G#

@Composable
fun DisplayUserNames() {
    var users by remember { mutableStateOf<List<User>>(emptyList()) }

    LaunchedEffect(true) {
        val retrofitClient = RetrofitClient()

        try {
            withContext(Dispatchers.IO) {
                val call = retrofitClient.api.getUsers()
                val response = call.execute()

                if (response.isSuccessful) {
                    users = response.body() ?: emptyList()
                } else {
                    // Log the error response if the request was not successful
                    Log.e("API_REQUEST", "Error: ${response.code()}")
                }
            }
        } catch (e: Exception) {
            // Log the exception
            Log.e("API_REQUEST", "Exception: $e")
        }
    }

    // Log the user list to check if it's updated
    Log.d("USER_LIST", "Users: $users")

    // Display the list of user names
    UserList(users = users)
}

// Composable function to display a list of user names
@Composable
fun UserList(users: List<User>) {
    LazyColumn {
        items(users) { user ->
            Text(text = user.name, modifier = Modifier.padding(16.dp))
        }
    }
}