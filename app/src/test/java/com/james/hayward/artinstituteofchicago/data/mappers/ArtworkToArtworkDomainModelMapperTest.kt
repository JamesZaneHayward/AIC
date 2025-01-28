package com.james.hayward.artinstituteofchicago.data.mappers

import com.james.hayward.artinstituteofchicago.data.Artwork
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ArtworkToArtworkDomainModelMapperTest {

    private lateinit var sut: ArtworkToArtworkDomainModelMapper

    private lateinit var artwork: Artwork

    @BeforeEach
    fun setup() {
        sut = ArtworkToArtworkDomainModelMapper()
        artwork = mockk<Artwork>().apply {
            every { this@apply.id } returns 5
            every { this@apply.title } returns "title"
            every { this@apply.artistDisplay } returns null
            every { this@apply.description } returns null
            every { this@apply.imageId } returns "imageId"
        }
    }

    @Test
    fun `given no imageId when toDomain is called then throw IllegalArgumentException`() {
        artwork.apply {
            every { this@apply.imageId } returns null
        }
        assertThrows<IllegalArgumentException> { sut.toDomain(artwork) }
    }

    @Test
    fun `given an Artwork when toDomain is executed then return expected id`() {
        val expectedId = 12
        artwork.apply {
            every { this@apply.id } returns expectedId
        }
        assertEquals(expectedId, sut.toDomain(artwork).id)
    }

    @Test
    fun `given an Artwork when toDomain is executed then return expected title`() {
        val expectedTitle = "The Starry Night"
        artwork.apply {
            every { this@apply.title } returns expectedTitle
        }
        assertEquals(expectedTitle, sut.toDomain(artwork).title)
    }

    @Test
    fun `given an Artwork with not null art display when toDomain is executed then return expected art display`() {
        val expectedArtDisplay = "expected art display"
        artwork.apply {
            every { this@apply.artistDisplay } returns expectedArtDisplay
        }
        assertEquals(expectedArtDisplay, sut.toDomain(artwork).artistDisplay)
    }

    @Test
    fun `given an Artwork with null art display when toDomain is executed then return domain model with null art display`() {
        artwork.apply {
            every { this@apply.artistDisplay } returns null
        }
        assertNull(sut.toDomain(artwork).artistDisplay)
    }

    @Test
    fun `given an Artwork with not null description when toDomain is executed then return expected description`() {
        val expectedDescription = "expected description"
        artwork.apply {
            every { this@apply.description } returns expectedDescription
        }
        assertEquals(expectedDescription, sut.toDomain(artwork).description)
    }

    @Test
    fun `given an Artwork with null description when toDomain is executed then return domain model with null description`() {
        artwork.apply {
            every { this@apply.description } returns null
        }
        assertNull(sut.toDomain(artwork).description)
    }

    @Test
    fun `given an imageId when toDomain is executed then return correct imageUrl`() {
        artwork.apply {
            every { this@apply.imageId } returns "123"
        }
        assertEquals(
            "https://www.artic.edu/iiif/2/123/full/843,/0/default.jpg",
            sut.toDomain(artwork).imageUrl
        )
    }

    @Test
    fun `given an imageId when toDomain is executed then return correct thumbnailUrl`() {
        artwork.apply {
            every { this@apply.imageId } returns "456"
        }
        assertEquals(
            "https://www.artic.edu/iiif/2/456/full/100,/0/default.jpg",
            sut.toDomain(artwork).thumbnailUrl
        )
    }
}
