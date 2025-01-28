package com.james.hayward.artinstituteofchicago.data

import com.james.hayward.artinstituteofchicago.data.common.ApiHandler
import com.james.hayward.artinstituteofchicago.data.common.ApiResponse
import com.james.hayward.artinstituteofchicago.data.mappers.ArtworkToArtworkDomainModelMapper
import com.james.hayward.artinstituteofchicago.domain.ArtworkDomainModel
import com.james.hayward.artinstituteofchicago.domain.ArtworkRepository
import javax.inject.Inject

class ArtworkRepositoryImpl @Inject constructor(
    private val apiService: AicApiService,
    private val artworkToArtworkDomainModelMapper: ArtworkToArtworkDomainModelMapper,
) : ArtworkRepository, ApiHandler {

    override suspend fun getArtworkList(): List<ArtworkDomainModel> {
        val apiResponse = handleApi { apiService.getArtworks() }

        when (apiResponse) {
            is ApiResponse.Error -> throw Exception() // TODO handle expected error codes
            is ApiResponse.Exception -> throw apiResponse.e
            is ApiResponse.Success -> {
                return apiResponse.response.data
                    .mapNotNull {
                        try {
                            artworkToArtworkDomainModelMapper.toDomain(it)
                        } catch (ignored: Exception) {
                            // if mapping fails, drop the entry
                            // TODO add logging to notify which entries are failing and why
                            null
                        }
                    }
            }
        }
    }

    override suspend fun getArtworkDetails(id: Int): ArtworkDomainModel {
        val apiResponse = handleApi { apiService.getArtworkById(id = id) }

        when (apiResponse) {
            is ApiResponse.Error -> throw Exception() // TODO handle expected error codes
            is ApiResponse.Exception -> throw apiResponse.e
            is ApiResponse.Success -> {
                return artworkToArtworkDomainModelMapper.toDomain(
                    apiResponse.response.data
                )
            }
        }
    }

}
