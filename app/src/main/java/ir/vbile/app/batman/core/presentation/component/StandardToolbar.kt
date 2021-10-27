package ir.vbile.app.batman.core.presentation.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ir.vbile.app.batman.R

@Composable
fun StandardToolbar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.background,
    onNavigateUp: () -> Unit = {},
    showBackArrow: Boolean = false,
    navActions: @Composable RowScope.() -> Unit = {},
    title: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = {
            if (showBackArrow) {
                IconButton(
                    onClick = onNavigateUp
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = stringResource(id = R.string.back),
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            }
        },
        actions = navActions,
        backgroundColor = backgroundColor,
        elevation = 0.dp
    )
}