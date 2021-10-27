package ir.vbile.app.batman.core.data.dto.remote

import ir.vbile.app.batman.BuildConfig
import ir.vbile.app.batman.core.data.dto.responses.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/")
    suspend fun searchMovie(
        @Query("s") query: String,
        @Query("page") page: Int?,
    ): SearchResponse

    companion object {
        const val BASE_URL = BuildConfig.STAGE_BASE_URL
    }
}