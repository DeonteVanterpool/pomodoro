package com.example.pomodoro.models

public class TaskBuilder() {
    var title: String = "";
    var estimatedPomodoros: Int = 0;

    fun build(): Task {
        return Task(this.title, this.estimatedPomodoros);
    }
}

public class Task (var title: String, val estimatedPomodoros: Int) {
    var currentPomodoros: Int = 0;
    var done: Boolean = false;

    fun complete() {
        this.done = true;
    }

    fun addPomo() {
        this.currentPomodoros++;
    }
}