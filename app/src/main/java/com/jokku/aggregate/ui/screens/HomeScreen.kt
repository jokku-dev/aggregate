package com.jokku.aggregate.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
//        topBar = { TopBar() }
    ) {
        Greeting(modifier = Modifier.padding(it), name = "Android")
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    AnimatedVisibility(visible = true) {
        TopAppBar(title = { Text(text = "AppBar")})
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Hello $name!",
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting("Android")
}