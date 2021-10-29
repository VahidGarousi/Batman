package ir.vbile.app.batman.feature_movie.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.vbile.app.batman.feature_movie.domain.models.MovieDetails

@Dao
interface MovieDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(movieDetails: MovieDetails)

    @Query("SELECT * FROM movie_details WHERE imdbID =:imdbID")
    suspend fun getMovieDetail(imdbID: String): MovieDetails
}