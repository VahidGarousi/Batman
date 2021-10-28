package ir.vbile.app.batman.feature_movie.data.repository

import androidx.paging.*
import ir.vbile.app.batman.R
import ir.vbile.app.batman.core.data.dto.local.BatmanDB
import ir.vbile.app.batman.feature_movie.data.remote.MovieApi
import ir.vbile.app.batman.feature_movie.domain.models.Movie
import ir.vbile.app.batman.feature_movie.domain.models.MovieDetails
import ir.vbile.app.batman.core.util.CoreConstants
import ir.vbile.app.batman.core.util.Resource
import ir.vbile.app.batman.core.util.UiText
import ir.vbile.app.batman.feature_movie.data.paging.MovieRemoteMediator
import ir.vbile.app.batman.feature_movie.domain.MovieRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class MovieRepositoryImpl constructor(
    private val api: MovieApi,
    private val db: BatmanDB
) : MovieRepository {
    override fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = MovieRemoteMediator(
                api, query, db
            )
        ) {
            db.movieDao().pagingSource(query)
        }.flow
    }

    override suspend fun getMovieDetails(movieId: String): Resource<MovieDetails> {
        return try {
            val response = api.getMovieDetails(movieId)
            Resource.Success(response.toMovieDetails())
        } catch (e: IOException) {
            Resource.Error(UiText.StringResource(R.string.error_could_not_reach))
        } catch (e: HttpException) {
            Resource.Error(UiText.StringResource(R.string.oops_something_went_wrong))
        }
    }
}