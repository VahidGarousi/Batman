package ir.vbile.app.batman.feature_movie.data.remote.responses

import ir.vbile.app.batman.feature_movie.domain.models.Movie

data class MovieDto(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
) {
    fun toMovie(): Movie {
        return Movie(
            imdbID = imdbID,
            poster = Poster,
            title = Title,
            type = Type,
            year = Year
        )
    }
}