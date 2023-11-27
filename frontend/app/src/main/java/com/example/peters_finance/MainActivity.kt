package com.example.peters_finance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.peters_finance.api.fetchUsers
import com.example.peters_finance.groups.GroupHomeOverview
import com.example.peters_finance.login.AccountPage
import com.example.peters_finance.login.CreateAccountPage
import com.example.peters_finance.login.LoginPage
import com.example.peters_finance.login.SplashScreen
import com.example.peters_finance.models.User


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var currentUser = remember{ mutableStateOf<User?>(null) }

            //Grab all users stored in the database
            var allUsers = emptyList<User>()
            LaunchedEffect(true){
                allUsers = fetchUsers()
            }

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") {
                    SplashScreen(navController)
                }

                composable("LoginPage") {
                    LoginPage(navController, allUsers, fetchUser = { fetchUser ->
                        currentUser.value = fetchUser
                    })
                }

                composable("CreateAccountPage") {
                    CreateAccountPage(navController, newUser = { newUser ->
                        currentUser.value = newUser
                    })
                }

                composable("GroupHomeOverview") {
                    GroupHomeOverview(navController, currentUser.value)
                }

                composable("AccountPage") {
                    AccountPage(navController, currentUser.value)
                }

            }


        }
    }
}

