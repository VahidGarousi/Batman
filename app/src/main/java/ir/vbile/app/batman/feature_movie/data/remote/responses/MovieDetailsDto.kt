package ir.vbile.app.batman.feature_movie.data.remote.responses

import ir.vbile.app.batman.feature_movie.domain.models.MovieDetails

data class MovieDetailsDto(
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
    val Ratings: List<RatingDto>,
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
    fun toMovieDetails(): MovieDetails {
        return MovieDetails(
            Actors = Actors,
            Awards = Awards,
            BoxOffice = BoxOffice,
            Country = Country,
            DVD = DVD,
            Director = Director,
            Genre = Genre,
            Language = Language,
            Metascore = Metascore,
            Plot = Plot,
            Poster = Poster,
            Production = Production,
            Rated = Rated,
            Ratings = Ratings.map { it.toRating() },
            Released = Released,
            Response = Response,
            Runtime = Runtime,
            Title = Title,
            Type = Type,
            Website = Website,
            Writer = Writer,
            Year = Year,
            imdbID = imdbID,
            imdbRating = imdbRating,
            imdbVotes = imdbVotes,
        )
    }
}