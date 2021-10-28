package ir.vbile.app.batman.feature_movie.data.paging

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.vbile.app.batman.feature_movie.domain.models.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Movie>)
    
    @Query("SELECT * FROM movies WHERE title LIKE :query")
    fun pagingSource(query: String): PagingSource<Int, Movie>

    @Query("DELETE FROM movies")
    suspend fun clearAll()
}