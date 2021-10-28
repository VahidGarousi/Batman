package ir.vbile.app.batman.feature_movie.domain.use_cases

import androidx.paging.PagingData
import ir.vbile.app.batman.feature_movie.domain.models.Movie
import ir.vbile.app.batman.feature_movie.domain.MovieRepository
import kotlinx.coroutines.flow.Flow

class SearchMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke(
        query: String
    ): Flow<PagingData<Movie>> {
        return repository.searchMovies(query)
    }
}