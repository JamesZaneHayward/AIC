package com.james.hayward.artinstituteofchicago.data

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class IndividualArtworkResponse(
    val data: Artwork
)