package ir.vbile.app.batman.feature_movie.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ir.vbile.app.batman.R
import ir.vbile.app.batman.core.presentation.component.StandardTextField
import ir.vbile.app.batman.core.presentation.ui.theme.*

@Composable
fun MoviesScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            TopAppBar(
                title = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpaceMedium),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onNavigate("")
                        },
                        modifier = Modifier.size(IconSizeMedium)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = stringResource(id = R.string.menu),
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
                            .clip(CircleShape)
                            .size(ProfilePictureSizeSmall)
                            .clickable {
                                onNavigate("")
                            }
                    )
                },
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            )
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
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
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    backgroundColor = MaterialTheme.colors.surface,
                    elevation = 4.dp,
                    shape = RoundedCornerShape(topStart = SpaceLarge)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(SpaceMedium)
                    ) {
                        Box(
                            Modifier.fillMaxWidth()
                        ) {
                            StandardTextField(
                                backgroundColor = MaterialTheme.colors.onSurface,
                                text = "",
                                hint = stringResource(id = R.string.search_hint),
                                onValueChanged = {

                                }
                            )
                            IconButton(
                                onClick = {

                                },
                                modifier = Modifier
                                    .padding(end = SpaceSmall)
                                    .align(Alignment.CenterEnd)
                                    .size(IconSizeLarge)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = stringResource(id = R.string.search_button),
                                    tint = Color.DarkGray
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(SpaceMedium))
                        LazyRow {
                            items(20) {
                                TopMovie()
                            }
                        }
                    }
                }
            }
        }
    }
}