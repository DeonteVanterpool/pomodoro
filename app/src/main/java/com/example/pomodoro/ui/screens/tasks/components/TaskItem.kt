package com.example.pomodoro.ui.screens.tasks.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pomodoro.models.Task
import com.example.pomodoro.models.TaskBuilder

sealed class TaskItemEvent {
    object Start : TaskItemEvent()
    object Delete : TaskItemEvent()
}

@Composable
fun TaskItem(task: Task, eventHandler: (TaskItemEvent) -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp).clickable(onClick = {eventHandler(TaskItemEvent.Start)}),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface, contentColor = MaterialTheme.colorScheme.onSurface),
    ) {
        Text(
            text = task.title,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        )
    }
}
