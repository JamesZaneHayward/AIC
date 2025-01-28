package com.james.hayward.artinstituteofchicago.data

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class ArtworksResponse(
    val pagination: Pagination,
    val data: List<Artwork>
) {
    data class Pagination(
        val total: Int,
        val limit: Int,
        val offset: Int,
        @Json(name = "total_pages") val totalPages: Int,
        @Json(name = "current_page") val currentPage: Int,
        @Json(name = "prev_url") val prevUrl: String?,
        @Json(name = "next_url") val nextUrl: String?,
    )
}