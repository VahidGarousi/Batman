package ir.vbile.app.batman.feature_movie.presentation.list

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.batman.core.util.Resource
import ir.vbile.app.batman.core.util.UiEvent
import ir.vbile.app.batman.core.util.UiText
import ir.vbile.app.batman.feature_movie.domain.use_cases.MovieUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MoviesViewModel @Inject constructor(
    @Named("immediateMain") private val defaultDispatcher: CoroutineDispatcher,
    private val movieUserCase: MovieUseCase
) : ViewModel() {
    private val _searchQuery = mutableStateOf("batman")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(MoviesScreenState())
    val state: State<MoviesScreenState> = _state

    private val _topMoviesLazyListState = mutableStateOf(LazyListState())
    val topMoviesLazyListState: State<LazyListState> = _topMoviesLazyListState

    private val _lazyListState = mutableStateOf(LazyListState())
    val lazyListState: State<LazyListState> = _lazyListState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch(defaultDispatcher) {
            loadMovies()
        }
    }

    private suspend fun loadMovies() {
        _state.value = _state.value.copy(
            isLoading = true
        )
        val result = movieUserCase.searchMovies(searchQuery.value)
        when (result) {
            is Resource.Success -> {
                _state.value = _state.value.copy(
                    movies = result.data,
                    moviesListIsEmptyAndInternetConnectIsNotAvailable = result.data?.isEmpty() == true,
                    isLoading = false
                )
            }
            is Resource.Error -> {
                _eventFlow.emit(UiEvent.ShowSnackBar(result.uiText ?: UiText.unknownError()))
                _state.value = _state.value.copy(
                    movies = result.data,
                    moviesListIsEmptyAndInternetConnectIsNotAvailable = result.data?.isEmpty() == true,
                    isLoading = false
                )
            }
        }
    }

    fun onEvent(event: MoviesScreenEvent) {
        when (event) {
            is MoviesScreenEvent.EnteredQuery -> {
                _searchQuery.value = event.query
            }
            is MoviesScreenEvent.Search -> {
                viewModelScope.launch {
                    loadMovies()
                }
            }
        }
    }
}