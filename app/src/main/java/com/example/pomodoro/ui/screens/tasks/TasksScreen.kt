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
import com.example.pomodoro.ui.screens.tasks.components.NewTaskButton
import com.example.pomodoro.ui.screens.tasks.components.TaskCreator
import com.example.pomodoro.ui.screens.tasks.components.TaskItem
import com.example.pomodoro.ui.screens.tasks.components.TaskItemList

@Composable
fun TasksScreen() {
    var currentTask by remember { mutableStateOf<TaskBuilder?>(null) }
    val taskList = remember { mutableStateListOf<Task>() }

    fun createNewTaskHandler() {
        currentTask = TaskBuilder()
    }

    fun cancelNewTaskHandler() {
        currentTask = null
    }

    fun newTaskHandler(text: String) {
        currentTask?.let {
            it.title = text
            taskList.add(it.build())
            currentTask = null
            return
        }
        throw UnsupportedOperationException("Current task is null!")
    }

    Column {
        TaskCreator(currentTask, { text -> newTaskHandler(text) }) // conditionally renders if the new task button has been clicked
        NewTaskButton({ createNewTaskHandler() }, { cancelNewTaskHandler() }, currentTask == null)
        TaskItemList(taskList)
    }
}