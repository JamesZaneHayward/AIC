package com.james.hayward.artinstituteofchicago.ui

import androidx.navigation.NavController
import com.james.hayward.artinstituteofchicago.ui.Destinations.ART_DETAIL_ROUTE
import com.james.hayward.artinstituteofchicago.ui.Destinations.ART_LIST_ROUTE
import kotlinx.serialization.Serializable

object Destinations {
    const val ART_LIST_ROUTE = "art_list"
    const val ART_DETAIL_ROUTE = "art_detail"
}

class NavigationActions(navController: NavController) {
    val navigateToArtList: () -> Unit = {
        navController.navigate(ART_LIST_ROUTE) {
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToArtDetail: () -> Unit = {
        navController.navigate(ART_DETAIL_ROUTE) {
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Serializable
data class ArtPiece(val id: Int)
