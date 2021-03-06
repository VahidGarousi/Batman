package ir.vbile.app.batman.core.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ir.vbile.app.batman.core.presentation.ui.Screen
import ir.vbile.app.batman.feature_auth.addAuthenticationGraph
import ir.vbile.app.batman.feature_movie.presentation.addMoviesGraph

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun AppNavigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MoviesScreen.route
    ) {
        addAuthenticationGraph(navController, scaffoldState)
        addMoviesGraph(navController, scaffoldState)
    }
}

