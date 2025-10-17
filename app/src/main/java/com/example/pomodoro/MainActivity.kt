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
import com.example.pomodoro.models.Task
import com.example.pomodoro.models.TaskBuilder
import com.example.pomodoro.ui.navigation.AppNavHost
import com.example.pomodoro.ui.navigation.AppScaffold
import com.example.pomodoro.ui.navigation.Destination
import com.example.pomodoro.ui.theme.AppTheme

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
    AppTheme {
        val navController = rememberNavController()
        var selectedDestination by rememberSaveable {
            mutableIntStateOf(Destination.TASKS.ordinal)
        }

        AppScaffold(
            selectedDestination = selectedDestination,
            onDestinationSelected = { index ->
                navController.navigate(Destination.entries[index].name)
                selectedDestination = index
            },
            modifier = Modifier
        ) { contentPadding ->
            AppNavHost(
                navController = navController,
                startDestination = Destination.TASKS.name,
                modifier = Modifier.padding(contentPadding)
            )
        }
    }
}
