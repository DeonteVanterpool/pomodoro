package com.example.pomodoro.ui.screens.tasks.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pomodoro.models.TaskBuilder

sealed class TaskCreatorEvent {
    object OpenTaskInput : TaskCreatorEvent()
    object CloseTaskInput : TaskCreatorEvent()
    data class SubmitTask(val task: TaskBuilder) : TaskCreatorEvent()
}

@Preview
@Composable
fun TaskCreator(dialogOpen: Boolean, eventHandler: (TaskCreatorEvent) -> Unit) {
    if (dialogOpen) {
        val state = rememberTextFieldState("")
        Column {
            Row {
                OutlinedTextField(
                    state = state,
                    label = { Text("Creating new task...") },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedContainerColor = MaterialTheme.colorScheme.surface
                    ),
                )
                Button({
                    val builder = TaskBuilder()
                    builder.title = state.text.toString()
                    eventHandler(TaskCreatorEvent.SubmitTask(builder))
                }) { Text("+") }
            }
            Button(onClick = { eventHandler(TaskCreatorEvent.CloseTaskInput) }) { Text("Cancel") }
        }

    } else {
        Button(onClick = { eventHandler(TaskCreatorEvent.OpenTaskInput) }) { Text("+ Create Task") }
    }
}

