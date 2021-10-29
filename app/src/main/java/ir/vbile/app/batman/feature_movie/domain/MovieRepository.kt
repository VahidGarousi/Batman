package ir.vbile.app.batman.feature_movie.domain

import androidx.paging.PagingData
import ir.vbile.app.batman.feature_movie.domain.models.Movie
import ir.vbile.app.batman.feature_movie.domain.models.MovieDetails
import ir.vbile.app.batman.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun searchMovies(query: String): Resource<List<Movie>>
    suspend fun getMovieDetails(movieId : String): Resource<MovieDetails>
}