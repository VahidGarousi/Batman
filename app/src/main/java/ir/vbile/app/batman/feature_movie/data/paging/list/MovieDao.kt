package ir.vbile.app.batman.feature_movie.data.paging.list

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.vbile.app.batman.feature_movie.domain.models.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Query("DELETE FROM movies")
    suspend fun clearAll()

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%'")
    suspend fun getMovies(query: String): List<Movie>?
}