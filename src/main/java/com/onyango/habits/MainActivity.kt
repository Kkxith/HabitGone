package com.onyango.habits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.onyango.habits.data.HabitDatabase
import com.onyango.habits.data.HabitRepository
import com.onyango.habits.ui.habit_gone.HabitsScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val habitDao = HabitDatabase.getDataBase(application).habitDao()
            HabitsScreen(habitRepository = HabitRepository(habitDao), navController = navController)

        }
    }
}