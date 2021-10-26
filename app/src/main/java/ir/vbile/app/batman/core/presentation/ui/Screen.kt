package ir.vbile.app.batman.core.presentation.ui

internal sealed class Screen(val route: String) {
    object MoviesScreen : Screen("movies_screen")
    object MovieDetailScreen : Screen("movie_detail_screen")
    object SplashScreen : Screen("splash_screen")
}
