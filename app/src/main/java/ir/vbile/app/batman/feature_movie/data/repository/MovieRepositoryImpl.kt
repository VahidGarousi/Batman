package ir.vbile.app.batman.feature_movie.data.repository

import androidx.paging.*
import androidx.room.withTransaction
import ir.vbile.app.batman.R
import ir.vbile.app.batman.core.data.dto.local.BatmanDB
import ir.vbile.app.batman.feature_movie.data.remote.MovieApi
import ir.vbile.app.batman.feature_movie.domain.models.Movie
import ir.vbile.app.batman.feature_movie.domain.models.MovieDetails
import ir.vbile.app.batman.core.util.Resource
import ir.vbile.app.batman.core.util.UiText
import ir.vbile.app.batman.feature_movie.domain.MovieRepository
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

@ExperimentalPagingApi
class MovieRepositoryImpl constructor(
    private val api: MovieApi,
    private val db: BatmanDB
) : MovieRepository {
    private val moviesDao = db.movieDao()
    private val movieDetailsDao = db.movieDetail()
    override suspend fun searchMovies(query: String): Resource<List<Movie>> {
        var localMovies = db.withTransaction {
            moviesDao.getMovies(query)
        }
        return try {
            val movies = api.searchMovie(query, 1)?.movies?.map {
                it.toMovie()
            } ?: listOf()
            db.withTransaction {
                moviesDao.insertAll(movies)
                localMovies = db.withTransaction {
                    moviesDao.getMovies(query)
                }
            }
            Resource.Success(movies)
        } catch (e: IOException) {
            Timber.i("")
            Resource.Error(
                data = localMovies,
                uiText = UiText.StringResource(R.string.error_could_not_reach)
            )
        } catch (e: HttpException) {
            Timber.i("")
            Resource.Error(
                data = localMovies,
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun getMovieDetails(movieId: String): Resource<MovieDetails> {
        var localMovieDetails = movieDetailsDao.getMovie(movieId)
        return try {
            val movieDetails = api.getMovieDetails(movieId).toMovieDetails()
            db.withTransaction {
                movieDetailsDao.insertOrReplace(movieDetails)
                localMovieDetails = movieDetailsDao.getMovie(movieId)
            }
            Resource.Success(localMovieDetails)
        } catch (e: IOException) {
            Resource.Error(
                data = localMovieDetails,
                uiText = UiText.StringResource(R.string.error_could_not_reach)
            )
        } catch (e: HttpException) {
            Resource.Error(
                data = localMovieDetails,
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }
}