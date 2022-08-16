package com.example.todotasker.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todotasker.feature_main_screen.presentation.MainScreen
import com.example.todotasker.feature_new_note.presentation.NewTaskScreen


@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Main().name){
        composable(Screens.Main().name){
            MainScreen(navController)
        }
        composable(Screens.NewNote().name){
            NewTaskScreen(navController = navController)
        }
    }
}