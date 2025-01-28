package com.james.hayward.artinstituteofchicago.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.james.hayward.artinstituteofchicago.domain.ArtworkDomainModel
import com.james.hayward.artinstituteofchicago.ui.components.LoadingSpinner

@Composable
fun ArtListRoute(
    viewModel: ArtListViewModel,
    innerPadding: PaddingValues,
    navigationAction: (id: Int) -> Unit,
) {
    val artListViewState by viewModel.artListViewState.collectAsStateWithLifecycle()
    when (val state = artListViewState) {
        ArtListViewModel.ArtListViewState.Loading -> {
            LoadingSpinner()
        }

        ArtListViewModel.ArtListViewState.NoArtworkAvailable -> {
            NoArtworkAvailable(innerPadding)
        }

        is ArtListViewModel.ArtListViewState.ArtList -> {
            ArtList(state.listOfArtwork, innerPadding) { navigationAction(it) }
        }
    }
}

@Composable
fun NoArtworkAvailable(
    innerPadding: PaddingValues,
) {
    Row(
        modifier = Modifier.padding(innerPadding)
    ) {
        Image(
            imageVector = Icons.Filled.Warning,
            contentDescription = null, // Decorative image, no alt text is provided
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = "No artwork available, please try again later.",
        )
    }
}

@Composable
fun ArtList(
    listOfArtwork: List<ArtworkDomainModel>,
    innerPadding: PaddingValues,
    onClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
    ) {
        items(listOfArtwork) { artwork ->
            ArtworkListing(
                artwork = artwork,
                onClick = onClick
            )
        }
    }
}

@Composable
fun ArtworkListing(
    artwork: ArtworkDomainModel,
    onClick: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(artwork.id)
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(all = 8.dp)
                .size(36.dp),
            model = artwork.thumbnailUrl,
            contentDescription = null,  // Decorative thumbnail, no alt text is provided

        )
        Column(
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 8.dp,
            )
        ) {
            Text(artwork.title)
            Text(artwork.artistDisplay)
        }
    }
    HorizontalDivider(
        thickness = 2.dp
    )
}

@Preview
@Composable
fun ArtworkListingPreview() {
    ArtworkListing(
        ArtworkDomainModel(
            id = 1,
            title = "The Starry Night",
            artistDisplay = "Vincent Van Gogh",
            description = null,
            imageUrl = "placeholder image url",
            thumbnailUrl = "placeholder thumbnail url",
        )
    ) { /* noop */ }
}
