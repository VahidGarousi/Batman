package ir.vbile.app.batman.feature_movie.presentation.list

sealed class MoviesScreenEvent {
    data class EnteredQuery(val query: String) : MoviesScreenEvent()
    object Search : MoviesScreenEvent()
    object Retry : MoviesScreenEvent()
}
