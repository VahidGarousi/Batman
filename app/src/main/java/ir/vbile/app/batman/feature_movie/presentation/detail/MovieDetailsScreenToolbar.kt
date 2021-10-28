package ir.vbile.app.batman.feature_movie.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ir.vbile.app.batman.R
import ir.vbile.app.batman.core.presentation.ui.theme.*

@Composable
fun MoviesScreenToolbar(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {},
        modifier = modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(
                onClick = {
                    onNavigateUp()
                },
                modifier = Modifier
                    .padding(SpaceExtraSmall)
                    .size(IconSizeMedium)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = stringResource(id = R.string.back),
                    tint = MaterialTheme.colors.onBackground,
                    modifier = Modifier
                )
            }
        },
        actions = {
            Image(
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = stringResource(id = R.string.tag_profile_image),
                modifier = Modifier
                    .padding(SpaceSmall)
                    .clip(CircleShape)
                    .size(ProfilePictureSizeExtraSmall)
                    .clickable {
                        onNavigateUp
                    }
            )
        },
        backgroundColor = MaterialTheme.colors.background.copy(alpha = 0.5f),
        elevation = 0.dp
    )
}
