package com.james.hayward.artinstituteofchicago.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.james.hayward.artinstituteofchicago.domain.ArtworkDomainModel
import com.james.hayward.artinstituteofchicago.domain.ArtworkRepository
import com.james.hayward.artinstituteofchicago.ui.ArtDetailViewModel.ArtDetailViewState.ArtDetails
import com.james.hayward.artinstituteofchicago.ui.ArtDetailViewModel.ArtDetailViewState.DetailsError
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = ArtDetailViewModel.ArtDetailViewModelFactory::class)
class ArtDetailViewModel @AssistedInject constructor(
    @Assisted val id: Int,
    val artworkRepository: ArtworkRepository,
) : ViewModel() {

    private val _artDetailViewState: MutableStateFlow<ArtDetailViewState> =
        MutableStateFlow(ArtDetailViewState.Loading)
    val artDetailViewState: StateFlow<ArtDetailViewState> = _artDetailViewState.asStateFlow()

    init {
        fetchArtDetails()
    }

    private fun fetchArtDetails() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                _artDetailViewState.update {
                    DetailsError
                }
            },
        ) {
            _artDetailViewState.update {
                ArtDetails(artworkRepository.getArtworkDetails(id))
            }
        }
    }

    sealed class ArtDetailViewState {
        object Loading : ArtDetailViewState()
        object DetailsError : ArtDetailViewState()
        data class ArtDetails(
            val artwork: ArtworkDomainModel
        ) : ArtDetailViewState()
    }

    @AssistedFactory
    interface ArtDetailViewModelFactory {
        fun create(id: Int): ArtDetailViewModel
    }
}
