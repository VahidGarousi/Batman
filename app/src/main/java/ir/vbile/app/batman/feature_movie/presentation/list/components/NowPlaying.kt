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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import ir.vbile.app.batman.core.domain.models.Movie
import ir.vbile.app.batman.core.presentation.component.Movie
import ir.vbile.app.batman.core.presentation.component.gridItems
import ir.vbile.app.batman.core.presentation.ui.theme.SpaceMedium


fun LazyListScope.latestMovies(
    movies: LazyPagingItems<Movie>,
    modifier: Modifier = Modifier
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
    val rows = if (movies.itemCount == 0) 0 else 1 + (movies.itemCount) - 1 / columns
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
                if (itemIndex < movies.itemCount) {
                    Box(
                        modifier = Modifier.weight(1f, fill = true),
                        propagateMinConstraints = true
                    ) {
                        Movie(
                            index = rowIndex,
                            movie = movies[itemIndex] ?: return@Row
                        )
                    }
                } else {
                    Spacer(Modifier.weight(1f, fill = true))
                }
            }
        }
    }
}
