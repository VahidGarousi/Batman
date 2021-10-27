package ir.vbile.app.batman.feature_movie.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ScaffoldState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ir.vbile.app.batman.core.presentation.ui.Screen
import ir.vbile.app.batman.feature_movie.presentation.detail.MovieDetailScreen
import ir.vbile.app.batman.feature_movie.presentation.list.MoviesScreen


@ExperimentalFoundationApi
fun NavGraphBuilder.addMoviesGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    composable(
        route = Screen.MoviesScreen.route
    ) {
        MoviesScreen(
            scaffoldState = scaffoldState,
            onNavigate = navController::navigate
        )
    }
    composable(
        route = Screen.MovieDetailScreen.route
    ) {
        MovieDetailScreen(
            scaffoldState = scaffoldState,
            onNavigate = navController::navigate
        )
    }
}