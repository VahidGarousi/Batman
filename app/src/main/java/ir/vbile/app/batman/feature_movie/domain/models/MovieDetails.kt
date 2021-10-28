package ir.vbile.app.batman.feature_movie.domain.models

import java.util.concurrent.TimeUnit

data class MovieDetails(
    val Actors: String,
    val Awards: String,
    val BoxOffice: String,
    val Country: String,
    val DVD: String,
    val Director: String,
    val Genre: String,
    val Language: String,
    val Metascore: String,
    val Plot: String,
    val Poster: String,
    val Production: String,
    val Rated: String,
    val Ratings: List<Rating>,
    val Released: String,
    val Response: String,
    val Runtime: String,
    val Title: String,
    val Type: String,
    val Website: String,
    val Writer: String,
    val Year: String,
    val imdbID: String,
    val imdbRating: String,
    val imdbVotes: String
) {
    fun formattedDuration(): String {
        return if (Runtime.takeWhile { it.isDigit() }.toLongOrNull() != null) {
            var millisecond = TimeUnit.MINUTES.toMillis(Runtime.takeWhile { it.isDigit() }.toLong())
            val hours = TimeUnit.MILLISECONDS.toHours(millisecond)
            millisecond -= TimeUnit.HOURS.toMillis(hours)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(millisecond)
            return "$hours hr $minutes Minutes"
        } else {
            "0"
        }
    }
}