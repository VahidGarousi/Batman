package ir.vbile.app.batman.core.data.dto.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.vbile.app.batman.core.util.RatingTypeConverter
import ir.vbile.app.batman.feature_movie.data.local.MovieDetailDao
import ir.vbile.app.batman.feature_movie.data.paging.list.MovieDao
import ir.vbile.app.batman.feature_movie.domain.models.Movie
import ir.vbile.app.batman.feature_movie.domain.models.MovieDetails

@Database(entities = [Movie::class, MovieDetails::class], version = 1)
@TypeConverters(RatingTypeConverter::class)
abstract class BatmanDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieDetail(): MovieDetailDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: BatmanDB? = null
        fun getDatabase(context: Context): BatmanDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BatmanDB::class.java, "batman_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}