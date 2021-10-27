package ir.vbile.app.batman.feature_movie.presentation.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ir.vbile.app.batman.R
import ir.vbile.app.batman.core.presentation.component.StandardSearchView
import ir.vbile.app.batman.core.presentation.ui.theme.SpaceLarge
import ir.vbile.app.batman.core.presentation.ui.theme.SpaceMedium
import ir.vbile.app.batman.feature_movie.presentation.list.components.MoviesScreenToolbar
import ir.vbile.app.batman.feature_movie.presentation.list.components.latestMovies
import ir.vbile.app.batman.feature_movie.presentation.list.components.topMovies

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MoviesScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            MoviesScreenToolbar(onNavigate)
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpaceMedium)
            ) {
                Text(
                    text = stringResource(id = R.string.slogan1, "Vahid"),
                    style = MaterialTheme.typography.h1.copy(fontWeight = FontWeight.Normal),
                    color = MaterialTheme.colors.secondary
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                Text(
                    text = stringResource(id = R.string.slogan2),
                    style = MaterialTheme.typography.h2
                )
            }
        }
        item {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(topStart = SpaceLarge)
            ) {
                StandardSearchView(
                    modifier = Modifier.padding(
                        top = SpaceMedium,
                        start = SpaceMedium,
                        end = SpaceMedium
                    )
                )
            }
        }
        topMovies()
        latestMovies()
    }
}