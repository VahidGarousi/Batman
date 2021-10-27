package ir.vbile.app.batman.core.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.request.ImageResult
import ir.vbile.app.batman.R
import ir.vbile.app.batman.core.domain.models.Movie
import ir.vbile.app.batman.core.presentation.ui.theme.*

@ExperimentalMaterialApi
@Composable
fun TopMovie(
    modifier: Modifier = Modifier,
    categories: List<String> = arrayListOf("Horror", "Theater"),
    movie: Movie
) {
    Card(
        modifier = modifier
            .width(240.dp)
            .height(120.dp)
            .padding(
                end = SpaceSmall,
                bottom = SpaceSmall
            ),
        shape = RoundedCornerShape(SpaceSmall),
        onClick = {
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberImagePainter(data = movie.poster),
                contentDescription = stringResource(id = R.string.movie_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .matchParentSize()
                    .background(MaterialTheme.colors.background.copy(alpha = 0.5f))
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(SpaceMedium)
            ) {
                Text(
                    text = movie.title.take(15),
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colors.onBackground,
                    overflow = TextOverflow.Ellipsis
                )
                Row {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = GoldColor,
                        modifier = Modifier.size(IconSizeSmall)
                    )
                    Text(
                        text = movie.imdbID,
                        style = MaterialTheme.typography.caption
                    )
                }
                Spacer(modifier = Modifier.height(SpaceMedium))
                Row {
                    categories.forEach { category ->
                        Text(
                            text = category,
                            style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Light),
                            color = MaterialTheme.colors.onBackground,
                            modifier = Modifier
                                .padding(end = SpaceSmall)
                                .clip(RoundedCornerShape(SpaceSmall))
                                .background(MaterialTheme.colors.onBackground.copy(alpha = 0.3f))
                                .padding(SpaceExtraSmall)
                        )
                    }
                }
            }
        }
    }
}