package ir.vbile.app.batman.feature_movie.presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import ir.vbile.app.batman.feature_movie.domain.models.Movie
import ir.vbile.app.batman.core.presentation.component.Movie
import ir.vbile.app.batman.core.presentation.ui.theme.SpaceMedium


fun LazyListScope.latestMovies(
    movies: List<Movie> = emptyList(),
    modifier: Modifier = Modifier,
    onMovieClick: (String) -> Unit = {}
) {
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .padding(SpaceMedium)
        ) {
            Text(
                text = "Latest Movies",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable(true) {

                    },
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Black)
            )
        }
    }
    val columns = 2
    val rows = if (movies.isEmpty()) 0 else 1 + (movies.size) - 1 / columns
    items(rows) { rowIndex ->
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = modifier
                .fillMaxWidth()
                .background(Color(0XFFFEFEFE))
                .padding(horizontal = SpaceMedium)
        ) {
            for (columnIndex in 0 until columns) {
                val itemIndex = rowIndex * columns + columnIndex
                if (itemIndex < movies.size) {
                    Box(
                        modifier = Modifier.weight(1f, fill = true),
                        propagateMinConstraints = true
                    ) {
                        Movie(
                            index = rowIndex,
                            movie = movies[itemIndex],
                            onMovieClick = onMovieClick
                        )
                    }
                } else {
                    Spacer(Modifier.weight(1f, fill = true))
                }
            }
        }
    }
}
