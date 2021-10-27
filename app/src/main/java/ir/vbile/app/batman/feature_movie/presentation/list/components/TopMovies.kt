package ir.vbile.app.batman.feature_movie.presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import ir.vbile.app.batman.core.domain.models.Movie
import ir.vbile.app.batman.core.presentation.component.TopMovie
import ir.vbile.app.batman.core.presentation.ui.theme.SpaceMedium


@ExperimentalMaterialApi
fun LazyListScope.topMovies(
    movies: LazyPagingItems<Movie>
) {
    item {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .padding(SpaceMedium)
        ) {
            Text(
                text = "Top Movies",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable(true) {

                    },
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Black)
            )
            Text(
                text = "See all",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable(true) {

                    },
                color = Color(0XFF979797)
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0XFFFEFEFE))
                .padding(bottom = SpaceMedium, start = SpaceMedium, end = SpaceMedium)
        ) {
            items(movies) { movie ->
                if (movie != null){
                    TopMovie(movie = movie)
                }
            }
        }
    }
}
