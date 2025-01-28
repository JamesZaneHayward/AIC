package com.james.hayward.artinstituteofchicago.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.james.hayward.artinstituteofchicago.ui.components.LoadingSpinner

@Composable
fun ArtDetailRoute(
    viewModel: ArtDetailViewModel,
    innerPadding: PaddingValues,
) {
    val artDetailViewState by viewModel.artDetailViewState.collectAsStateWithLifecycle()
    when (val state = artDetailViewState) {
        ArtDetailViewModel.ArtDetailViewState.Loading -> {
            LoadingSpinner()
        }

        ArtDetailViewModel.ArtDetailViewState.DetailsError -> {
            NoArtworkAvailable(innerPadding)
        }

        is ArtDetailViewModel.ArtDetailViewState.ArtDetails -> {
            ArtDetail(
                innerPadding,
                state.artwork.title,
                state.artwork.imageUrl,
                state.artwork.description,
            )
        }
    }
}

@Composable
fun ArtDetail(
    innerPadding: PaddingValues,
    title: String,
    imageUrl: String,
    description: String?,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding)
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = title,
            style = MaterialTheme.typography.headlineLarge
        )
        AsyncImage(
            modifier = Modifier.padding(top = 16.dp),
            model = imageUrl,
            contentDescription = null,
        )
        description?.let {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = it,
            )
        }
    }
}

@Preview
@Composable
fun ArtDetailPreview() {
    ArtDetail(
        innerPadding = PaddingValues(0.dp),
        title = "The Starry Night",
        imageUrl = "placeholder",
        description = "The starry night by Vincent Van Gogh.",
    )
}
