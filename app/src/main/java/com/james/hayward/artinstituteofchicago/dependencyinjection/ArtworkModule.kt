package com.james.hayward.artinstituteofchicago.dependencyinjection

import com.james.hayward.artinstituteofchicago.data.ArtworkRepositoryImpl
import com.james.hayward.artinstituteofchicago.domain.ArtworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ArtworkModule {

    @Binds
    @Singleton
    abstract fun bindArtworkRepository(
        artworkRepositoryImpl: ArtworkRepositoryImpl,
    ): ArtworkRepository
}
