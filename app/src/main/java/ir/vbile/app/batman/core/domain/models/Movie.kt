package ir.vbile.app.batman.core.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val imdbID: String,
    val poster: String,
    val title: String,
    val type: String,
    val year: String
)