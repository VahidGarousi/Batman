package ir.vbile.app.batman.core.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.vbile.app.batman.R
import ir.vbile.app.batman.core.presentation.ui.theme.*

@Composable
fun Movie(
    index: Int = 0,
    title: String = "Batman",
    rate: String = "8.2/10"
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                end = if (index % 2 == 0) SpaceSmall else 0.dp,
                start = if (index % 2 == 0) 0.dp else SpaceSmall,
                bottom = SpaceMedium
            )
            .clickable {

            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.poster),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(SpaceLarge)),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(SpaceSmall))
        Text(
            text = title,
            style = MaterialTheme.typography.h2.copy(fontSize = 18.sp),
            color = MaterialTheme.colors.background
        )
        Spacer(modifier = Modifier.height(SpaceSmall))
        Row() {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = OrangeColor,
                modifier = Modifier.size(IconSizeSmall)
            )
            Text(
                text = rate,
                style = MaterialTheme.typography.body2,
                color = Color(0XFFAFAEB6)
            )
        }
    }
}