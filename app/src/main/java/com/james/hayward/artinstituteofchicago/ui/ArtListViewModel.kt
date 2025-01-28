package com.james.hayward.artinstituteofchicago.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.james.hayward.artinstituteofchicago.domain.ArtworkDomainModel
import com.james.hayward.artinstituteofchicago.domain.ArtworkRepository
import com.james.hayward.artinstituteofchicago.ui.ArtListViewModel.ArtListViewState.ArtList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtListViewModel @Inject constructor(
    private val repository: ArtworkRepository,
) : ViewModel() {

    private val _artListViewState: MutableStateFlow<ArtListViewState> =
        MutableStateFlow(ArtListViewState.Loading)
    val artListViewState: StateFlow<ArtListViewState> = _artListViewState.asStateFlow()

    init {
        getArtList()
    }

    private fun getArtList() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                _artListViewState.update {
                    ArtListViewState.NoArtworkAvailable
                }
            },
        ) {
            _artListViewState.update {
                ArtList(repository.getArtworkList())
            }
        }
    }

    sealed class ArtListViewState {
        object Loading : ArtListViewState()
        object NoArtworkAvailable : ArtListViewState()
        data class ArtList(
            val listOfArtwork: List<ArtworkDomainModel>
        ) : ArtListViewState()
    }
}
