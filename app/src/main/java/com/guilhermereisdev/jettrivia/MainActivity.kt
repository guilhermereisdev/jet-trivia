package com.guilhermereisdev.jettrivia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.guilhermereisdev.jettrivia.screens.TriviaHome
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TriviaHome()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TriviaPreview() {
    TriviaHome()
}
