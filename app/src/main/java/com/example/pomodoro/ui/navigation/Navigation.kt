package com.example.pomodoro.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun AppScaffold(
    selectedDestination: Int,
    onDestinationSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (innerPadding: androidx.compose.foundation.layout.PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            AppNavigationBar(
                selectedDestination = selectedDestination,
                onDestinationSelected = onDestinationSelected
            )
        },
        modifier = modifier
    ) { contentPadding ->
        content(contentPadding)
    }
}

@Composable
fun AppNavigationBar(
    selectedDestination: Int,
    onDestinationSelected: (Int) -> Unit
) {
    NavigationBar {
        Destination.entries.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = selectedDestination == index,
                onClick = {
                    onDestinationSelected(index)
                },
                label = { Text(destination.getLabel()) },
                icon = {},
            )
        }
    }
}