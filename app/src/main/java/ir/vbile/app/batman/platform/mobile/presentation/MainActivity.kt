package ir.vbile.app.batman.platform.mobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.vbile.app.batman.core.presentation.AppNavigation
import ir.vbile.app.batman.core.presentation.ui.theme.BatmanTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BatmanTheme(true) {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    val scaffoldState = rememberScaffoldState()
                    AppNavigation(
                        navController = navController,
                        scaffoldState = scaffoldState
                    )
                }
            }
        }
    }
}