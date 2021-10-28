package ir.vbile.app.batman.feature_movie.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey(autoGenerate = false)
    val label: String,
    val nextKey: Int?
)