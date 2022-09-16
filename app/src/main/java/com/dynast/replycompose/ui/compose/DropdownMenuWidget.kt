package com.dynast.replycompose.ui.compose

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dynast.replycompose.R
import com.dynast.replycompose.data.compose.allUserAccounts
import com.dynast.replycompose.ui.theme.ReplyComposeTheme
import com.dynast.replycompose.ui.theme.emphasisDisabledAlpha

@Composable
fun DropdownMenuWidget(
    modifier: Modifier = Modifier
) {
    var emailValue by remember { mutableStateOf(allUserAccounts[0].email) }
    var isOpen by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { isOpen = true },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            text = emailValue,
            color = MaterialTheme.colorScheme.onSurface
        )
        DropdownMenu(
            modifier = Modifier
                .widthIn(min = 340.dp)
                .background(MaterialTheme.colorScheme.surface),
            expanded = isOpen, onDismissRequest = { isOpen = false }) {
            allUserAccounts.forEach {
                DropdownMenuItem(
                    onClick = {
                        isOpen = false
                        emailValue = it.email
                    }
                ) {
                    Text(
                        text = it.email,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
        IconButton(onClick = { isOpen = true }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_drop_down),
                contentDescription = "Dropdown",
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
fun DropdownMenuWidgetPreview() {
    ReplyComposeTheme {
        DropdownMenuWidget()
    }
}