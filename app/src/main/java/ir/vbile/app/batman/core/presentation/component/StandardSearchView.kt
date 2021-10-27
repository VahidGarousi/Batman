package ir.vbile.app.batman.core.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ir.vbile.app.batman.R
import ir.vbile.app.batman.core.presentation.ui.theme.IconSizeLarge
import ir.vbile.app.batman.core.presentation.ui.theme.SpaceSmall

@Composable
fun StandardSearchView(
    modifier: Modifier = Modifier,
    text: String,
    onValueChanged: (String) -> Unit
) {
    Box(
        modifier.fillMaxWidth()
    ) {
        StandardTextField(
            backgroundColor = Color.LightGray,
            text = text,
            hint = stringResource(id = R.string.search_hint),
            onValueChanged = onValueChanged
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
}