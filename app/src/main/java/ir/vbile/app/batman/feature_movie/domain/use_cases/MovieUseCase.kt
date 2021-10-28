package ir.vbile.app.batman.feature_movie.domain.use_cases

data class MovieUseCase(
    val searchMovies: SearchMoviesUseCase,
     val getMovieDetails: GetMovieDetailsUseCase
)
