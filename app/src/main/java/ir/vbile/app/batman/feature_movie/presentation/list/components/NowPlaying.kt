package ir.vbile.app.batman.feature_movie.presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import ir.vbile.app.batman.core.presentation.component.Movie
import ir.vbile.app.batman.core.presentation.component.gridItems
import ir.vbile.app.batman.core.presentation.ui.theme.SpaceMedium


fun LazyListScope.latestMovies(
    modifier: Modifier = Modifier,
    cells: Int = 2,
    count: Int = 10
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
    gridItems(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0XFFFEFEFE))
            .padding(horizontal = SpaceMedium),
        cells = cells,
        count = count
    ) { index ->
        Movie(index = index)
    }
}
