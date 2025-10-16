package com.example.pomodoro

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

val taskList = arrayOf(Task);

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewMessageCard();
        }
    }
}

@Composable
fun PreviewMessageCard() {
    Text(text = "Hello Android!")
}


@Composable
fun MessageCard(name: String) {
    Text(text = "Hello $name!")
}
