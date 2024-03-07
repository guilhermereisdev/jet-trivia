package com.guilhermereisdev.jettrivia.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.guilhermereisdev.jettrivia.component.Questions
import com.guilhermereisdev.jettrivia.ui.theme.JetTriviaTheme

@Composable
fun TriviaHome(viewModel: QuestionViewModel = hiltViewModel()) {
    JetTriviaTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Questions(viewModel)
        }
    }
}
