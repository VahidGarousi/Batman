package ir.vbile.app.batman.feature_movie.presentation.list

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.batman.feature_movie.domain.use_cases.MovieUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MoviesViewModel @Inject constructor(
    @Named("immediateMain") private val defaultDispatcher: CoroutineDispatcher,
    private val movieUserCase: MovieUseCase
) : ViewModel() {
    private val _searchQuery = mutableStateOf("batman")
    val searchQuery: State<String> = _searchQuery

    private val _topMoviesLazyListState = mutableStateOf(LazyListState())
    val topMoviesLazyListState: State<LazyListState> = _topMoviesLazyListState

    private val _lazyListState = mutableStateOf(LazyListState())
    val lazyListState : State<LazyListState> = _lazyListState

    val movies = movieUserCase
        .searchMovies(searchQuery.value)
        .cachedIn(viewModelScope)

    fun onEvent(event: MoviesScreenEvent) {
        when (event) {
            is MoviesScreenEvent.EnteredQuery -> {
                _searchQuery.value = event.query
            }
            is MoviesScreenEvent.Search -> {

            }
        }
    }
}