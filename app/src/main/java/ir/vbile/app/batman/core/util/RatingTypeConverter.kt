package ir.vbile.app.batman.core.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import ir.vbile.app.batman.feature_movie.domain.models.Rating

class RatingTypeConverter {
    private val gson: Gson = Gson()

    @TypeConverter
    fun fromString(value: String?): List<Rating> {
        return gson.fromJson(value, Array<Rating>::class.java).asList()
    }

    @TypeConverter
    fun toGson(ratings: List<Rating>): String {
        return gson.toJson(ratings)
    }
}