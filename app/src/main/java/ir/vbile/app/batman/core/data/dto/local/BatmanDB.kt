package ir.vbile.app.batman.core.data.dto.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.vbile.app.batman.feature_movie.domain.models.Movie
import ir.vbile.app.batman.feature_movie.data.paging.MovieDao

@Database(entities = [Movie::class], version = 1)
abstract class BatmanDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao

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
                    BatmanDB::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}