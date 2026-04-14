package com.example.pomodoro.ui.screens.pomodoro.components

import android.view.WindowManager
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import kotlin.math.absoluteValue
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration
import kotlin.time.toKotlinDuration

sealed class TimerEvent {
    object Start: TimerEvent()
    object Pause: TimerEvent()
    object Complete: TimerEvent()
}

@Composable
fun Timer(eventListener: (TimerEvent) -> Unit) {
    var remainingTime by remember { mutableStateOf(50.minutes) }
    Text(
        text = formatTime(remainingTime),
        style = MaterialTheme.typography.displayLarge,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(top = 16.dp),
    )
    val activity = LocalActivity.current
    DisposableEffect(Unit) {
        val window = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        onDispose {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }
    LaunchedEffect(Unit) {
        val endTime =
            LocalDateTime.now().plus(remainingTime.toJavaDuration())
        while(true) {
            delay(1.seconds)
            remainingTime = java.time.Duration.between(LocalDateTime.now(), endTime).toKotlinDuration()
        }
    }
}

fun formatTime(time: Duration): String {
    var minutes = ""
    var seconds = ""
    time.toComponents { _, m, s, _ -> {minutes = "${m.absoluteValue}"; seconds = "${s.absoluteValue}"} }()

    return (if (time.isNegative()) "-" else "") + minutes.padStart(2, '0') + ":" + seconds.padStart(2, '0')
}