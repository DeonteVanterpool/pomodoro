package com.example.pomodoro.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pomodoro.models.Task
import com.example.pomodoro.ui.screens.pomodoro.PomodoroScreen
import com.example.pomodoro.ui.screens.tasks.TaskScreenEvent
import com.example.pomodoro.ui.screens.tasks.TasksScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    val taskList = remember { mutableStateListOf<Task>() }
    fun taskScreenEventHandler(e: TaskScreenEvent) {
        when (e) {
            is TaskScreenEvent.AddTask -> {
                taskList.add(e.task)
            }
        }
    }
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },

    ) {
        composable(Destination.TASKS.name) {A
            TasksScreen(taskList, { e -> taskScreenEventHandler(e) })
        }
        composable(Destination.POMODORO.name) {
            PomodoroScreen()
        }
    }
}
