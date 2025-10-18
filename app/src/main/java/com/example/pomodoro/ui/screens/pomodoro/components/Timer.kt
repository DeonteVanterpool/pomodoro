package com.example.pomodoro.ui.screens.pomodoro.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.pomodoro.ui.TasksViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.time.delay
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

sealed class TimerEvent {
    object Start: TimerEvent()
    object Pause: TimerEvent()
    object Complete: TimerEvent()
}

@Composable
fun Timer(eventListener: (TimerEvent) -> Unit) {
    var currentTime by remember { mutableStateOf(50.minutes) }
    Text(
        text = formatTime(currentTime),
        style = MaterialTheme.typography.displayLarge,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(top = 16.dp),
    )
    LaunchedEffect(Unit) {
        while(true) {
            delay(1.seconds)
            currentTime = currentTime.minus(1.seconds)
        }
    }
}

fun formatTime(time: Duration): String {
    var minutes = ""
    var seconds = ""
    time.toComponents { _, m, s, _ -> {minutes = "$m"; seconds = "$s"} }()

    return minutes.padStart(2, '0') + ":" + seconds.padStart(2, '0')
}