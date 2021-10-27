package ir.vbile.app.batman.core.domain.use_case

import androidx.paging.PagingData
import ir.vbile.app.batman.core.domain.models.Movie
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