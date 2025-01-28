package com.james.hayward.artinstituteofchicago.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun AicNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.ART_LIST_ROUTE,
    innerPadding: PaddingValues,
) {
    Surface {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,
        ) {
            composable(
                route = Destinations.ART_LIST_ROUTE,
            ) { backStackEntry ->
                val viewModel = hiltViewModel<ArtListViewModel>()
                ArtListRoute(
                    viewModel,
                    innerPadding,
                ) { navController.navigate(route = ArtPiece(id = it)) }
            }
            composable<ArtPiece> { backStackEntry ->
                val artPiece: ArtPiece = backStackEntry.toRoute()
                val viewModel =
                    hiltViewModel<ArtDetailViewModel, ArtDetailViewModel.ArtDetailViewModelFactory> { factory ->
                        factory.create(artPiece.id)
                    }
                ArtDetailRoute(
                    viewModel,
                    innerPadding,
                )
            }
        }
    }
}
