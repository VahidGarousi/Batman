package ir.vbile.app.batman.di

import androidx.paging.ExperimentalPagingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.vbile.app.batman.core.data.dto.local.BatmanDB
import ir.vbile.app.batman.feature_movie.data.remote.MovieApi
import ir.vbile.app.batman.feature_movie.domain.use_cases.GetMovieDetailsUseCase
import ir.vbile.app.batman.feature_movie.domain.use_cases.MovieUseCase
import ir.vbile.app.batman.feature_movie.domain.use_cases.SearchMoviesUseCase
import ir.vbile.app.batman.feature_movie.data.repository.MovieRepositoryImpl
import ir.vbile.app.batman.feature_movie.domain.MovieRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
object MovieModule {
    @Provides
    @Singleton
    fun provideMoviesApi(
        client: OkHttpClient
    ): MovieApi =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(MovieApi.BASE_URL)
            .build()
            .create(MovieApi::class.java)

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: MovieApi, db: BatmanDB
    ): MovieRepository = MovieRepositoryImpl(api, db)


    @Provides
    @Singleton
    fun provideMovieUseCase(repository: MovieRepository): MovieUseCase {
        return MovieUseCase(
            searchMovies = SearchMoviesUseCase(repository),
            getMovieDetails = GetMovieDetailsUseCase(repository)
        )
    }
}