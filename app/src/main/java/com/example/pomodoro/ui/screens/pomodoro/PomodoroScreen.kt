package com.example.pomodoro.ui.screens.pomodoro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.pomodoro.ui.TasksViewModel
import androidx.compose.runtime.collectAsState
import com.example.pomodoro.ui.screens.pomodoro.components.Timer

@Composable
fun PomodoroScreen(viewModel: TasksViewModel = hiltViewModel()) {
    val currentTaskIndex: Int = viewModel.currentTask.collectAsState().value
    val currentTask: String = if (currentTaskIndex != -1) viewModel.tasks.collectAsState().value[currentTaskIndex].title else "No task selected"
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Pomodoro Timer",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = currentTask,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 16.dp)
        )
        Timer {  }
    }
}
