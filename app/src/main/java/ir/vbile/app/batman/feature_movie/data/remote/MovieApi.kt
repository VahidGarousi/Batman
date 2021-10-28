package ir.vbile.app.batman.feature_movie.data.remote

import ir.vbile.app.batman.BuildConfig
import ir.vbile.app.batman.feature_movie.data.remote.responses.MovieDetailsDto
import ir.vbile.app.batman.feature_movie.data.remote.responses.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/")
    suspend fun searchMovie(
        @Query("s") query: String,
        @Query("page") page: Int?,
    ): SearchResponse

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") imdbId: String
    ): MovieDetailsDto

    companion object {
        const val BASE_URL = BuildConfig.STAGE_BASE_URL
    }
}