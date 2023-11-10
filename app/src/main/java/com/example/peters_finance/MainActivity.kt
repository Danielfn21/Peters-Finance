package com.example.peters_finance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.peters_finance.groups.GroupHomeOverview
import com.example.peters_finance.login.SplashScreen



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "splash") {
                composable("splash"){
                    SplashScreen(navController)
                }

                composable("groupHomeOverview"){
                    GroupHomeOverview()
                }
            }




        }
        }
}

