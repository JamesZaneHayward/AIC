package com.james.hayward.artinstituteofchicago.domain

interface ArtworkRepository {
    suspend fun getArtworkList(): List<ArtworkDomainModel>
    suspend fun getArtworkDetails(id: Int): ArtworkDomainModel
}
