package ir.vbile.app.batman.feature_auth

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ir.vbile.app.batman.core.presentation.ui.Screen
import ir.vbile.app.batman.feature_auth.presentaion.splash.SplashScreen

fun NavGraphBuilder.addAuthenticationGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    composable(
        route = Screen.SplashScreen.route
    ) {
        SplashScreen(
            scaffoldState = scaffoldState,
            onNavigate = navController::navigate
        )
    }
}
