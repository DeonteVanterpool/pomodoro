package com.example.pomodoro.ui

import android.util.MutableInt
import androidx.lifecycle.ViewModel
import com.example.pomodoro.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor() : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList<Task>())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    private val _currentTask = MutableStateFlow<Int>(-1)
    val currentTask: StateFlow<Int> = _currentTask.asStateFlow()

    fun addTask(task: Task) {
        _tasks.value = _tasks.value + task
    }

    fun startTask(index: Int) {
        _currentTask.value = index
    }

    fun deleteTask(index: Int) {
        _tasks.value = _tasks.value.filterIndexed { i, _ -> i != index }
    }
}