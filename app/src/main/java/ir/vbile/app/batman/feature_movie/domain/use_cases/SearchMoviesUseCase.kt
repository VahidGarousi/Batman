package ir.vbile.app.batman.feature_movie.domain.use_cases

import ir.vbile.app.batman.core.util.Resource
import ir.vbile.app.batman.feature_movie.domain.MovieRepository
import ir.vbile.app.batman.feature_movie.domain.models.Movie

class SearchMoviesUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(
        query: String
    ): Resource<List<Movie>> {
        return repository.searchMovies(query)
    }
}