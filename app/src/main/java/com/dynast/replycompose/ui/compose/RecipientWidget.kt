package com.dynast.replycompose.ui.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dynast.replycompose.R
import com.dynast.replycompose.ui.components.RecipientChip
import com.dynast.replycompose.ui.theme.ReplyComposeTheme
import com.dynast.replycompose.ui.theme.emphasisDisabledAlpha

@Composable
fun RecipientWidget(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(horizontal = 8.dp)) {
        val painter = rememberAsyncImagePainter(R.drawable.dummy)
        LazyRow(modifier = modifier.weight(1f)) {
            items(3) {
                RecipientChip(avatar = painter) {}
            }
        }

        IconButton(onClick = { }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_circle_outline),
                contentDescription = "Add",
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = emphasisDisabledAlpha)
            )
        }
    }
}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Night",
    backgroundColor = 0xFF000000
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Day"
)
@Composable
fun RecipientWidgetPreview() {
    ReplyComposeTheme {
        RecipientWidget()
    }
}