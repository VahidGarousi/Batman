package ir.vbile.app.batman.di

import androidx.paging.ExperimentalPagingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.vbile.app.batman.BuildConfig
import ir.vbile.app.batman.core.data.dto.local.BatmanDB
import ir.vbile.app.batman.core.data.dto.remote.MovieApi
import ir.vbile.app.batman.core.domain.use_case.MovieUseCase
import ir.vbile.app.batman.core.domain.use_case.SearchMoviesUseCase
import ir.vbile.app.batman.feature_movie.data.repository.MovieRepositoryImpl
import ir.vbile.app.batman.feature_movie.domain.MovieRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
object MovieModule {
    @Provides
    @Singleton
    @YaraRetrofit
    internal fun provideYaraRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.STAGE_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


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

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class YaraRetrofit


    @Provides
    @Singleton
    fun provideMovieRepository(
        api: MovieApi, db: BatmanDB
    ): MovieRepository = MovieRepositoryImpl(api, db)


    @Provides
    @Singleton
    fun provideMovieUseCase(repository: MovieRepository): MovieUseCase {
        return MovieUseCase(searchMovies = SearchMoviesUseCase(repository))
    }
}