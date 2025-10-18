package com.example.pomodoro.ui.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.pomodoro.models.Task
import com.example.pomodoro.models.TaskBuilder
import com.example.pomodoro.ui.TasksViewModel
import com.example.pomodoro.ui.screens.tasks.components.TaskCreator
import com.example.pomodoro.ui.screens.tasks.components.TaskCreatorEvent
import com.example.pomodoro.ui.screens.tasks.components.TaskItemList
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

sealed class TaskScreenEvent {
    data class AddTask(val task: Task) : TaskScreenEvent()
}

@Composable
fun TasksScreen(tasks: List<Task>, eventHandler: (TaskScreenEvent) -> Unit) {
    var buildingTask by remember { mutableStateOf<Boolean>(false) }

    fun taskCreatorEventHandler(e: TaskCreatorEvent) {
        when (e) {
            is TaskCreatorEvent.OpenTaskInput -> {
                buildingTask = true
            }
            is TaskCreatorEvent.CloseTaskInput -> {
                buildingTask = false
            }
            is TaskCreatorEvent.SubmitTask -> {
                if (buildingTask) {
                    eventHandler(TaskScreenEvent.AddTask(e.task.build()))
                    buildingTask = false
                } else {
                    throw UnsupportedOperationException("Current task is null!")
                }
            }
        }
    }

    Column {
        TaskCreator(buildingTask, { e -> taskCreatorEventHandler(e) }) // conditionally renders if the new task button has been clicked
        TaskItemList(tasks = tasks)
    }
}
