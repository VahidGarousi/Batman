package ir.vbile.app.batman.feature_movie.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.batman.core.domain.models.Movie
import ir.vbile.app.batman.core.domain.use_case.MovieUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MoviesViewModel @Inject constructor(
    @Named("immediateMain") private val defaultDispatcher: CoroutineDispatcher,
    private val movieUserCase: MovieUseCase
) : ViewModel() {
    private val _searchQuery = mutableStateOf("batman")
    val searchQuery: State<String> = _searchQuery
    val movies = movieUserCase.searchMovies(searchQuery.value).cachedIn(viewModelScope)
    fun onEvent(event: MoviesScreenEvent) {
        when (event) {
            is MoviesScreenEvent.EnteredQuery -> {
                _searchQuery.value = event.query
            }
        }
    }
}