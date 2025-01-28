package com.james.hayward.artinstituteofchicago.data.mappers

import com.james.hayward.artinstituteofchicago.data.Artwork
import com.james.hayward.artinstituteofchicago.domain.ArtworkDomainModel
import javax.inject.Inject

class ArtworkToArtworkDomainModelMapper @Inject constructor() :
    Mapper<Artwork, ArtworkDomainModel> {
    override fun toDomain(artwork: Artwork): ArtworkDomainModel {
        artwork.imageId?.let {
            return ArtworkDomainModel(
                id = artwork.id,
                title = artwork.title,
                artistDisplay = artwork.artistDisplay,
                description = artwork.description,
                // TODO extract image mapping to its own mapper for reusability and testability
                imageUrl = "https://www.artic.edu/iiif/2/${artwork.imageId}/full/843,/0/default.jpg",
                thumbnailUrl = "https://www.artic.edu/iiif/2/${artwork.imageId}/full/100,/0/default.jpg",
            )
        } ?: throw IllegalArgumentException(
            "Missing Image Id for id: ${artwork.id} title: ${artwork.title}"
        )
    }
}
