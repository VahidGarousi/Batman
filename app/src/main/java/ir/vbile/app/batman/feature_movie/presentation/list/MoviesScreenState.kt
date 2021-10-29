package ir.vbile.app.batman.feature_movie.presentation.list

import ir.vbile.app.batman.feature_movie.domain.models.Movie

data class MoviesScreenState(
    val movies: List<Movie>? = emptyList(),
    val isLoading: Boolean = false,
    val moviesListIsEmptyAndInternetConnectIsNotAvailable: Boolean = false
)
