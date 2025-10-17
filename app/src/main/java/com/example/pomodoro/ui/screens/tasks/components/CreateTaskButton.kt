package com.example.pomodoro.ui.screens.tasks.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun NewTaskButton(handler: () -> Unit, handler2: () -> Unit, show: Boolean) {
    val text = if (show) "+ Create Task" else "Cancel"
    Button(onClick = { if (show) handler() else handler2() }) { Text(text) }
}