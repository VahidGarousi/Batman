package ir.vbile.app.batman.feature_movie.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val imdbID: String,
    val poster: String,
    val title: String,
    val type: String,
    val year: String,
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    val pageNumber: Int,
    val updatedAt: Long = System.currentTimeMillis()
) {
    var indexInResponse: Int = -1
}