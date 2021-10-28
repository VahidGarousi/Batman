package ir.vbile.app.batman.feature_movie.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.withTransaction
import ir.vbile.app.batman.core.data.dto.local.BatmanDB
import ir.vbile.app.batman.feature_movie.data.remote.MovieApi
import ir.vbile.app.batman.feature_movie.domain.models.Movie
import retrofit2.HttpException
import java.io.IOException

class MovieSource constructor(
    private val api: MovieApi,
    private val query: String,
    private val database: BatmanDB
) : PagingSource<Int, Movie>() {
    private val moviesDao = database.movieDao()

    private var currentPage = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: currentPage
            val response = api.searchMovie(query = query, page = nextPage)
            database.withTransaction {
                if (response.movies.isNotEmpty())
                    moviesDao.clearAll()
                // Insert new movies into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                moviesDao.insertAll(response.movies.map { it.toMovie() })
            }
            LoadResult.Page(
                data = response.movies.map { it.toMovie() },
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (response.movies.isEmpty()) null else currentPage + 1
            ).also {
                currentPage++
            }

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = state.anchorPosition
}