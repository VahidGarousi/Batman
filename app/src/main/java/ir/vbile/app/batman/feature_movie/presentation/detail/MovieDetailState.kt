package ir.vbile.app.batman.feature_movie.presentation.detail

import ir.vbile.app.batman.feature_movie.domain.models.MovieDetails

data class MovieDetailState(
    val movieDetails: MovieDetails? = null,
    val isLoading: Boolean = false,
    val moviesIsEmptyAndInternetConnectIsNotAvailable: Boolean = false
)
