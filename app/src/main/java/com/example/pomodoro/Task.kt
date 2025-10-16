package com.example.pomodoro

class Task (var title: String, val estimatedPomodoros: int) {
    var currentPomodoros: int = 0;
    var done: boolean = false;

    fun complete() {
        this.done = true;
    }

    fun addPomo() {
        this.currentPomodoros++;
    }
}