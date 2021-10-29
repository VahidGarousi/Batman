package ir.vbile.app.batman.core.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ir.vbile.app.batman.R
import ir.vbile.app.batman.core.presentation.ui.theme.SpaceLarge
import ir.vbile.app.batman.core.presentation.ui.theme.StandardButtonPadding

@Composable
fun ConnectionLost(
    onRetry: () -> Unit = {},
) {
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
                    .align(Alignment.CenterHorizontally)
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
                    onRetry()
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