package ir.vbile.app.batman.feature_movie.domain

import androidx.paging.PagingData
import ir.vbile.app.batman.core.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun searchMovies(query: String): Flow<PagingData<Movie>>
}