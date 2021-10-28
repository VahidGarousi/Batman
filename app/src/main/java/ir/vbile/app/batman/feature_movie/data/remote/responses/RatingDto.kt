package ir.vbile.app.batman.feature_movie.data.remote.responses

import ir.vbile.app.batman.feature_movie.domain.models.Rating

data class RatingDto(
    val Source: String,
    val Value: String
) {
    fun toRating(): Rating {
        return Rating(
            Source = Source,
            Value = Value
        )
    }
}