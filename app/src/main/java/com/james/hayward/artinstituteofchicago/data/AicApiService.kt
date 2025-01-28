package com.james.hayward.artinstituteofchicago.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AicApiService {
    @GET("api/v1/artworks")
    suspend fun getArtworks(
        @Query("fields") fields: String = "id,title,artist_display,image_id,description",
        @Query("limit") limit: Int = 50,
    ): Response<ArtworksResponse>

    @GET("api/v1/artworks/{id}")
    suspend fun getArtworkById(
        @Path("id") id: Int,
        @Query("fields") fields: String = "id,title,artist_display,image_id,description",
    ): Response<IndividualArtworkResponse>
}
