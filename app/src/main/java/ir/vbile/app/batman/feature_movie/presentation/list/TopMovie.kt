package ir.vbile.app.batman.feature_movie.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.constrain
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import ir.vbile.app.batman.R
import ir.vbile.app.batman.core.presentation.ui.theme.*

@Composable
fun TopMovie(
    modifier: Modifier = Modifier,
    title: String = "The Batman",
    duration: String = "2 hr 20 minutes",
    rate: Float = 8.2f,
    categories: List<String> = arrayListOf("Horror", "Theater")
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(
                end = SpaceSmall,
                bottom = SpaceSmall
            ),
        shape = RoundedCornerShape(SpaceSmall)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.movie_image),
                contentDescription = stringResource(id = R.string.movie_image)
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
                    text = title,
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colors.onBackground
                )
                Row {
                    Text(
                        text = duration,
                        style = MaterialTheme.typography.caption
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = GoldColor,
                        modifier = Modifier.size(IconSizeSmall)
                    )
                    Text(
                        text = rate.toString(),
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