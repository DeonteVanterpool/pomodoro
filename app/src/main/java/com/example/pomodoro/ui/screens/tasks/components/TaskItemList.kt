package com.example.pomodoro.ui.screens.tasks.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pomodoro.models.Task

sealed class TaskItemListEvent {
    data class DeleteTask(val index: Int) : TaskItemListEvent()
    data class StartTask(val index: Int) : TaskItemListEvent()
}

@Preview
@Composable
fun TaskItemList(tasks: List<Task>, eventHandler: (TaskItemListEvent) -> Unit) {

    fun taskItemEventHandler(event: TaskItemEvent, index: Int) {
        when (event) {
            TaskItemEvent.Delete -> eventHandler(TaskItemListEvent.DeleteTask(index))
            TaskItemEvent.Start -> eventHandler(TaskItemListEvent.StartTask(index))
        }
    }

    Column {
        Text("Tasks: ${tasks.size}", color = MaterialTheme.colorScheme.onBackground)
        tasks.forEachIndexed { index, task ->
            TaskItem(task = task, eventHandler = { e -> taskItemEventHandler(e, index) })
        }
    }
}
