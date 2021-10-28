package ir.vbile.app.batman.feature_movie.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import ir.vbile.app.batman.R
import ir.vbile.app.batman.core.presentation.ui.theme.*
import ir.vbile.app.batman.core.presentation.util.asString
import ir.vbile.app.batman.core.util.UiEvent
import ir.vbile.app.batman.core.util.toPx
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@Composable
fun MovieDetailScreen(
    scaffoldState: ScaffoldState,
    onNavigateUp: () -> Unit = {},
    onNavigate: (String) -> Unit = {},
    vm: MovieDetailViewModel = hiltViewModel()
) {
    val lazyListState = rememberLazyListState()
    val toolbarState = vm.toolbarState.value
    val toolbarHeightCollapsed = 72.dp
    val bannerHeight = (LocalConfiguration.current.screenWidthDp / 3f).dp
    val toolbarHeightExpanded = remember {
        bannerHeight + ProfilePictureSizeLarge
    }
    val maxOffset = remember {
        toolbarHeightExpanded - toolbarHeightCollapsed
    }
    val state = vm.state.value
    val context = LocalContext.current
    val nestedScrollConnection = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            val delta = available.y
            if (delta > 0f && lazyListState.firstVisibleItemIndex != 0) {
                return Offset.Zero
            }
            val newOffset = vm.toolbarState.value.toolbarOffsetY + delta
            vm.setToolbarOffsetY(
                newOffset.coerceIn(
                    minimumValue = -maxOffset.toPx(),
                    maximumValue = 0f
                )
            )
            val newRatio =
                (vm.toolbarState.value.toolbarOffsetY + maxOffset.toPx()) / maxOffset.toPx()
            vm.setExpandedRatio(newRatio)
            return Offset.Zero
        }
    }
    LaunchedEffect(true) {
        vm.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event.route)
                }
                UiEvent.NavigateUp -> {
                    onNavigateUp()
                }
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.uiText.asString(context))
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        Box(
            modifier = Modifier
                .height(
                    (bannerHeight * toolbarState.expandedRatio).coerceIn(
                        minimumValue = toolbarHeightCollapsed,
                        maximumValue = bannerHeight
                    )
                )
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = state.movieDetails?.Poster,
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = constraints.maxHeight.toFloat()
                            )
                        )
                ) {

                }
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState
        ) {
            item {
                Spacer(
                    modifier = Modifier
                        .height(toolbarHeightExpanded)
                )
            }
            item {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MovieDescription(state)
                    MovieRates(state)
                    Column(
                        modifier = Modifier
                            .offset(y = (-26).dp)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(topStart = SpaceExtraLarge))
                            .background(Color(0XFF1B222A))
                    ) {
                        MovieSummary(state)
                        Spacer(modifier = Modifier.height(SpaceMedium))
                        MoviePlot(state.movieDetails?.Plot.orEmpty())
                        Actors(state.movieDetails?.Actors.orEmpty())
                        Writers(state.movieDetails?.Writer.orEmpty())
                    }
                }
            }
        }
        MoviesScreenToolbar(
            onNavigateUp = onNavigateUp,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun Writers(writers: String) {
    val writerList = writers.split(',')
    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = -SpaceLarge)
            .padding(
                start = SpaceMedium,
                end = SpaceMedium
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.actors),
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(SpaceMedium))
        Row {
            writerList.forEach { actor ->
                Text(
                    text = actor,
                    style = MaterialTheme.typography.caption.copy(
                        color = Color.White,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(SpaceSmall)
                        .clip(RoundedCornerShape(SpaceSmall))
                        .background(Color(0XFF262D35))
                        .padding(SpaceSmall)
                )
            }
        }
    }
}

@Composable
fun Actors(actors: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = -SpaceLarge)
            .padding(
                start = SpaceMedium,
                end = SpaceMedium
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.actors),
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(SpaceMedium))
        val actorsList = actors.split(',')
        Row {
            actorsList.forEach { actor ->
                Text(
                    text = actor,
                    style = MaterialTheme.typography.caption.copy(
                        color = Color.White,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(SpaceSmall)
                        .clip(RoundedCornerShape(SpaceSmall))
                        .background(Color(0XFF262D35))
                        .padding(SpaceSmall)
                )
            }
        }
    }
}

@Composable
private fun MoviePlot(plot: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = -SpaceLarge)
            .padding(
                start = SpaceMedium,
                end = SpaceMedium
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.plot),
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(SpaceMedium))
        Text(
            text = plot,
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
private fun MovieSummary(state: MovieDetailState) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(SpaceLarge)
    ) {
        Row {
            state.movieDetails?.Director?.let {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.33f)
                ) {
                    Text(
                        text = stringResource(id = R.string.director),
                        style = MaterialTheme.typography.caption.copy(
                            color = Color.White,
                            fontSize = 14.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = it,
                        style = MaterialTheme.typography.caption.copy(
                            color = Color.White,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(SpaceSmall)
                            .clip(RoundedCornerShape(SpaceSmall))
                            .background(Color(0XFF262D35))
                            .padding(SpaceSmall)
                    )
                }
            }
            state.movieDetails?.Country?.let {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.33f)
                ) {
                    Text(
                        text = stringResource(id = R.string.country),
                        style = MaterialTheme.typography.caption.copy(
                            color = Color.White,
                            fontSize = 14.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = it,
                        style = MaterialTheme.typography.caption.copy(
                            color = Color.White,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(SpaceSmall)
                            .clip(RoundedCornerShape(SpaceSmall))
                            .background(Color(0XFF262D35))
                            .padding(SpaceSmall)
                    )
                }
            }
            state.movieDetails?.Language?.let {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.33f)
                ) {
                    Text(
                        text = stringResource(id = R.string.language),
                        style = MaterialTheme.typography.caption.copy(
                            color = Color.White,
                            fontSize = 14.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = it,
                        style = MaterialTheme.typography.caption.copy(
                            color = Color.White,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(SpaceSmall)
                            .clip(RoundedCornerShape(SpaceSmall))
                            .background(Color(0XFF262D35))
                            .padding(SpaceSmall)
                    )
                }
            }
        }
    }
}

@Composable
private fun MovieRates(state: MovieDetailState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = SpaceExtraLarge
                )
            )
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0XFF2B2194), Color(0XFF421EBC))
                )
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                bottom = SpaceMedium,
                top = 0.dp,
                start = SpaceMedium,
                end = SpaceMedium
            )
        ) {
            state.movieDetails?.Ratings?.forEach {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.33f)
                        .padding(top = SpaceLarge)
                ) {
                    Text(
                        text = if (it.Source == "Internet Movie Database") stringResource(
                            id = R.string.imdb
                        ) else it.Source,
                        style = MaterialTheme.typography.caption.copy(
                            color = Color.White,
                            fontSize = 14.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = it.Value,
                        style = MaterialTheme.typography.caption.copy(
                            color = Color.White,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(SpaceSmall)
                            .clip(RoundedCornerShape(SpaceSmall))
                            .background(Color(0XFF5545C9))
                            .padding(SpaceMedium)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(SpaceMedium))
    }
}

@Composable
private fun MovieDescription(state: MovieDetailState) {
    Column(
        modifier = Modifier.padding(SpaceMedium)
    ) {
        Text(
            text = state.movieDetails?.Title ?: "",
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(SpaceMedium))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val genres = state.movieDetails?.Genre?.split(',')?.take(3)
            genres?.forEach {
                Text(
                    text = it,
                    style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Light),
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier
                        .padding(end = SpaceSmall)
                        .clip(RoundedCornerShape(SpaceSmall))
                        .background(Color(0XFF222222).copy(alpha = 0.6f))
                        .padding(SpaceSmall)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SpaceMedium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    val imdbText = stringResource(id = R.string.imdb)
                    val boldStyle = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onBackground
                    )
                    val normalStyle = SpanStyle(
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colors.onBackground,
                        fontSize = 12.sp
                    )
                    withStyle(boldStyle) {
                        append(state.movieDetails?.imdbRating ?: "")
                    }
                    append("   ")
                    withStyle(normalStyle) {
                        append(imdbText)
                    }
                }
            )
            state.movieDetails?.formattedDuration()?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.caption.copy(fontSize = 14.sp)
                )
            }
            state.movieDetails?.Released?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.caption.copy(fontSize = 14.sp)
                )
            }
        }
    }
}