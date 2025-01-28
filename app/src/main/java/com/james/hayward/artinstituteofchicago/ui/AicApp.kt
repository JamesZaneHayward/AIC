package com.james.hayward.artinstituteofchicago.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun AicApp() {
    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        NavigationActions(navController)
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        AicNavGraph(
            navController = navController,
            innerPadding = innerPadding,
        )
    }
}
