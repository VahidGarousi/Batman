package ir.vbile.app.batman.feature_movie.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.vbile.app.batman.core.data.dto.local.BatmanDB
import ir.vbile.app.batman.core.data.dto.remote.MovieApi
import ir.vbile.app.batman.core.domain.models.Movie
import ir.vbile.app.batman.core.util.CoreConstants
import ir.vbile.app.batman.feature_movie.data.paging.MovieSource
import ir.vbile.app.batman.feature_movie.domain.MovieRepository
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class MovieRepositoryImpl constructor(
    private val api: MovieApi,
    private val db: BatmanDB
) : MovieRepository {
    override fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = CoreConstants.DEFAULT_PAGE_SIZE)
        ) {
            MovieSource(api = api, query = query, db)
        }.flow
    }
}