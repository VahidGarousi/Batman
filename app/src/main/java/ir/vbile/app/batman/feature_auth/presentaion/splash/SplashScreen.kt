package ir.vbile.app.batman.feature_auth.presentaion.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SplashScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit = {}
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Register",
            color = Color.White,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}