package com.james.hayward.artinstituteofchicago.domain

data class ArtworkDomainModel(
    val id: Int,
    val title: String,
    val artistDisplay: String?,
    val description: String?,
    val imageUrl: String,
    val thumbnailUrl: String,
)
