package com.example.pomodoro.ui.screens.tasks.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.pomodoro.models.Task
import com.example.pomodoro.ui.TasksViewModel
import kotlin.collections.forEach


@Preview
@Composable
fun TaskItemList(tasks: List<Task>) {
    Column {
        Text("Tasks: ${tasks.size}", color = MaterialTheme.colorScheme.onBackground)
        tasks.forEach { task ->
            TaskItem(task = task, click = { TODO() })
        }
    }
}
