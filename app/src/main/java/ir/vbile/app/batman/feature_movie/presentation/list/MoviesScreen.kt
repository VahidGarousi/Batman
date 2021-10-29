package ir.vbile.app.batman.feature_movie.presentation.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import ir.vbile.app.batman.R
import ir.vbile.app.batman.core.presentation.component.StandardSearchView
import ir.vbile.app.batman.core.presentation.ui.Screen
import ir.vbile.app.batman.core.presentation.ui.theme.SpaceExtraLarge
import ir.vbile.app.batman.core.presentation.ui.theme.SpaceLarge
import ir.vbile.app.batman.core.presentation.ui.theme.SpaceMedium
import ir.vbile.app.batman.core.presentation.ui.theme.StandardButtonPadding
import ir.vbile.app.batman.core.presentation.util.ConnectionState
import ir.vbile.app.batman.core.presentation.util.asString
import ir.vbile.app.batman.core.util.UiEvent
import ir.vbile.app.batman.core.utils.compose.ConnectivityStatus
import ir.vbile.app.batman.core.utils.compose.ConnectivityStatusBox
import ir.vbile.app.batman.core.utils.compose.connectivityState
import ir.vbile.app.batman.feature_movie.presentation.list.components.MoviesScreenToolbar
import ir.vbile.app.batman.feature_movie.presentation.list.components.latestMovies
import ir.vbile.app.batman.feature_movie.presentation.list.components.topMovies
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Named

@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MoviesScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    vm: MoviesViewModel = hiltViewModel()
) {
    // This will cause re-composition on every network state change
    val connection by connectivityState()
    val topMovies = vm.state.value.movies ?: emptyList()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    LaunchedEffect(true) {
        vm.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event.route)
                UiEvent.NavigateUp -> onNavigateUp()
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
            }
        }
    }
    Column {
        ConnectivityStatus(connection)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = vm.lazyListState.value
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
                        text = vm.searchQuery.value,
                        modifier = Modifier.padding(
                            top = SpaceMedium,
                            start = SpaceMedium,
                            end = SpaceMedium
                        ),
                        onValueChanged = { vm.onEvent(MoviesScreenEvent.EnteredQuery(it)) },
                        onSearchClick = { vm.onEvent(MoviesScreenEvent.Search) }
                    )
                }
            }
            topMovies(
                topMovies,
                onMovieClick = { movieId ->
                    onNavigate(Screen.MovieDetailScreen.route + "?movieId=$movieId")
                },
                vm.topMoviesLazyListState.value
            )
            latestMovies(topMovies, onMovieClick = { movieId ->
                onNavigate(Screen.MovieDetailScreen.route + "?movieId=$movieId")
            }
            )
        }
    }
    if (vm.state.value.isLoading) {
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
    if (vm.state.value.moviesListIsEmptyAndInternetConnectIsNotAvailable) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.onBackground),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(SpaceLarge)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.no_internet),
                    contentDescription = null,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .aspectRatio(2.0f)
                )
                Spacer(modifier = Modifier.height(SpaceLarge))
                Text(
                    text = stringResource(id = R.string.internet_not_available),
                    color = MaterialTheme.colors.background,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(SpaceLarge))
                Text(
                    text = stringResource(id = R.string.internet_not_available_hint),
                    color = MaterialTheme.colors.background,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(SpaceLarge))
                Button(
                    onClick = {
                        val isConnected = connection === ConnectionState.Available
                        if (isConnected) {
                            vm.onEvent(MoviesScreenEvent.Search)
                        } else {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    context.getString(R.string.internet_not_available_hint)
                                )
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.onBackground,
                        contentColor = MaterialTheme.colors.error
                    ),
                    shape = MaterialTheme.shapes.medium,
                    border = BorderStroke(1.dp, MaterialTheme.colors.error),
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(StandardButtonPadding)
                ) {
                    Text(
                        text = stringResource(id = R.string.refresh),
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
        }
    }
}