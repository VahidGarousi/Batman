package ir.vbile.app.batman.feature_movie.domain.use_cases

import ir.vbile.app.batman.feature_movie.domain.models.MovieDetails
import ir.vbile.app.batman.core.util.Resource
import ir.vbile.app.batman.feature_movie.domain.MovieRepository

class GetMovieDetailsUseCase constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(
        movieId: String
    ): Resource<MovieDetails> {
        return repository.getMovieDetails(movieId)
    }
}