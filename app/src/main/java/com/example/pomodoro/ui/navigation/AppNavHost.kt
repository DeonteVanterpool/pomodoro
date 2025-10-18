package com.example.pomodoro.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pomodoro.models.Task
import com.example.pomodoro.ui.TasksViewModel
import com.example.pomodoro.ui.screens.pomodoro.PomodoroScreen
import com.example.pomodoro.ui.screens.tasks.TaskScreenEvent
import com.example.pomodoro.ui.screens.tasks.TasksScreen
import androidx.compose.runtime.collectAsState

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel = hiltViewModel()
) {
    fun taskScreenEventHandler(e: TaskScreenEvent) {
        when (e) {
            is TaskScreenEvent.StartTask -> navController.navigate(Destination.POMODORO.name)
            is TaskScreenEvent.AddTask -> {}
            is TaskScreenEvent.DeleteTask -> {}
        }
    }
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },

    ) {
        composable(Destination.TASKS.name) {
            TasksScreen(tasks = viewModel.tasks.collectAsState().value, { e -> taskScreenEventHandler(e) }, viewModel)
        }
        composable(Destination.POMODORO.name) {
            PomodoroScreen(viewModel)
        }
    }
}
