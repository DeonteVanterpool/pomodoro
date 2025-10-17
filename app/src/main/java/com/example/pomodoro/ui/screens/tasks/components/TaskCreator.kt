package com.example.pomodoro.ui.screens.tasks.components

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

@Preview
@Composable
fun TaskCreator(currentTask: TaskBuilder?, newTaskHandler: (String) -> Unit) {
    currentTask?.let {
        val state = rememberTextFieldState(it.title)
        Row {
            OutlinedTextField(
                state = state,
                label = { Text("Creating new task...") },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface
                ),
            )
            Button({ newTaskHandler(state.text.toString()) }) { Text("+") }
        }
    }
}

