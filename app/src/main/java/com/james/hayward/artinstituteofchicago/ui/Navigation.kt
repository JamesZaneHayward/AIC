package com.james.hayward.artinstituteofchicago.ui

import kotlinx.serialization.Serializable

object Destinations {
    const val ART_LIST_ROUTE = "art_list"
}

@Serializable
data class ArtPiece(val id: Int)
