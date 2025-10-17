package com.example.pomodoro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(content = { App() })
        }
    }
}

@Preview
@Composable
fun App() {
    val navController = rememberNavController()
    var selectedDestination by rememberSaveable { mutableIntStateOf(Destination.TASKS.ordinal) }

    val navigate = { s: String -> navController.navigate(s)}

    Scaffold(
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(destination.name)
                            selectedDestination = index
                        },
                        label = { Text(destination.getLabel()) },
                        icon = {},
                    )
                }
            }
        }
    ) { contentPadding ->
        AppNavHost(
            navController = navController,
            startDestination = Destination.TASKS.name,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Destination.TASKS.name) {
            TasksScreen()
        }
        composable(Destination.POMODORO.name) {
            PomodoroScreen()
        }
    }
}

@Composable
fun TasksScreen() {
    var currentTask by remember { mutableStateOf<TaskBuilder?>(null) }
    val taskList = remember { mutableStateListOf<Task>() }

    fun createNewTaskHandler() {
        currentTask = TaskBuilder()
    }

    fun cancelNewTaskHandler() {
        currentTask = null
    }

    fun newTaskHandler(text: String) {
        currentTask?.let {
            it.title = text
            taskList.add(it.build())
            currentTask = null
            return
        }
        throw UnsupportedOperationException("Current task is null!")
    }

    Column {
        NewTask(currentTask, { text -> newTaskHandler(text) })
        NewTaskButton({ createNewTaskHandler() }, { cancelNewTaskHandler() }, currentTask == null)
        RenderTasks(taskList)
    }
}

@Composable
fun PomodoroScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Pomodoro Timer",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Timer will be implemented here",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

enum class Destination {
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

@Preview
@Composable
fun RenderTasks(tasks: List<Task>) {
    Column {
        Text("Tasks: ${tasks.size}", color = MaterialTheme.colorScheme.onBackground)
        tasks.forEach { task ->
            TaskItem(task = task, click = { TODO() })
        }
    }
}

@Composable
fun TaskItem(task: Task, click: (Task) -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp).clickable(onClick = {click(task)}),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface, contentColor = MaterialTheme.colorScheme.onSurface),
    ) {
        Text(
            text = task.title,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        )
    }
}

@Preview
@Composable
fun NewTask(currentTask: TaskBuilder?, newTaskHandler: (String) -> Unit) {
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

@Preview
@Composable
fun NewTaskButton(handler: () -> Unit, handler2: () -> Unit, show: Boolean) {
    val text = if (show) "+ Create Task" else "Cancel"
    Button(onClick = { if (show) handler() else handler2() }) { Text(text) }
}
