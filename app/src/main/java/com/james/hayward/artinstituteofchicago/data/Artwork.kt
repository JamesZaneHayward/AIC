package com.james.hayward.artinstituteofchicago.data

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Artwork(
    val id: Int,
    val title: String,
    @Json(name = "artist_display") val artistDisplay: String?,
    val description: String?,
    @Json(name = "image_id") val imageId: String?,
)
