package ir.vbile.app.batman.feature_movie.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ir.vbile.app.batman.core.data.dto.local.BatmanDB
import ir.vbile.app.batman.feature_movie.data.local.RemoteKey
import ir.vbile.app.batman.feature_movie.data.remote.MovieApi
import ir.vbile.app.batman.feature_movie.domain.models.Movie
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.TimeUnit

@ExperimentalPagingApi
class MovieRemoteMediator constructor(
    private val api: MovieApi,
    private val query: String,
    private val database: BatmanDB
) : RemoteMediator<Int, Movie>() {
    private val moviesDao = database.movieDao()
    private val remoteKeyDao = database.remoteKeyDao()
    var pageNumber = 1
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Movie>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = database.withTransaction {
                        remoteKeyDao.remoteKeyByQuery(query)
                    }
                    remoteKey?.let {
                        if (remoteKey.nextKey == null) {
                            return MediatorResult.Success(
                                endOfPaginationReached = true
                            )
                        }
                        remoteKey.nextKey
                    }
                }
            }
            val response = api.searchMovie(query, loadKey)
            val movies = response?.movies?.map {
                it.toMovie(pageNumber).apply { indexInResponse = pageNumber }
            } ?: listOf()
            pageNumber = ++pageNumber
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.deleteByQuery(query)
                    moviesDao.deleteByQuery(query)
                }
                // Update RemoteKey for this query.
                remoteKeyDao.insertOrReplace(RemoteKey(query, pageNumber))
                // Insert new movies into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                moviesDao.insertAll(movies)
            }
            MediatorResult.Success(endOfPaginationReached = movies.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
        val lastUpdatedAt = database.withTransaction {
            moviesDao.lastUpdated()
        }
        Timber.i("loadFromCache => ${System.currentTimeMillis() - lastUpdatedAt <= cacheTimeout}")
        return if (System.currentTimeMillis() - lastUpdatedAt >= cacheTimeout) {
            // Cached data is up-to-date, so there is no need to re-fetch
            // from the network.
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            // Need to refresh cached data from network; returning
            // LAUNCH_INITIAL_REFRESH here will also block RemoteMediator's
            // APPEND and PREPEND from running until REFRESH succeeds.
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }
}