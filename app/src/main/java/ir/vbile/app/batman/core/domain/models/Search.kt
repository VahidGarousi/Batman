package ir.vbile.app.batman.core.domain.models



data class Search(
    val imdbID: String,
    val poster: String,
    val title: String,
    val type: String,
    val year: String
)