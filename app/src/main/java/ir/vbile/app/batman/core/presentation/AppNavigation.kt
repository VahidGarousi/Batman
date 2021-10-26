package ir.vbile.app.batman.core.presentation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ir.vbile.app.batman.core.presentation.ui.Screen
import ir.vbile.app.batman.feature_auth.addAuthenticationGraph
import ir.vbile.app.batman.feature_movie.presentation.addMoviesGraph

@Composable
fun AppNavigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        addAuthenticationGraph(navController, scaffoldState)
        addMoviesGraph(navController, scaffoldState)
    }
}

