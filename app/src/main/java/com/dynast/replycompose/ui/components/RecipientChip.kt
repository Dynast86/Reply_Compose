package com.dynast.replycompose.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dynast.replycompose.data.compose.Account
import com.dynast.replycompose.data.email.EmailStore
import com.dynast.replycompose.ui.theme.ReplyComposeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipientChip(
    modifier: Modifier = Modifier,
    item: Account,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.padding(horizontal = 2.dp),
        onClick = onClick,
        shape = CircleShape,
        color = MaterialTheme.colorScheme.background,
    ) {
        Row(
            modifier = modifier
                .defaultMinSize(minHeight = ChipDefaults.MinHeight)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
                .padding(end = horizontalPadding),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                painter = rememberAsyncImagePainter(model = item.avatar), contentDescription = item.fullName,
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(horizontalPadding))
            Text(
                text = item.fullName,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(Modifier.width(horizontalPadding))
        }
    }
}

private val horizontalPadding = 8.dp

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
fun RecipientChipPreview() {
    ReplyComposeTheme {
        RecipientChip(item = listOf(EmailStore.getDefaultUserAccount())[0]) {

        }
    }
}