package com.example.pomodoro.ui.navigation

public enum class Destination {
    TASKS,
    POMODORO;

    fun getLabel(): String {
        return if (this == TASKS) {
            "Tasks"
        } else {
            "Pomodoro"
        }
    }
}