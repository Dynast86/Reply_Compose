package com.dynast.replycompose.ui.email

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.dynast.replycompose.R
import com.dynast.replycompose.ui.nav.NavItem
import com.dynast.replycompose.ui.theme.emphasisMediumAlpha

@Composable
fun EmailSubjectWidget(
    modifier: Modifier = Modifier,
    subject: String,
    onClick: () -> Unit
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.weight(1f),
            text = subject,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = NavItem.Close.route,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = emphasisMediumAlpha)
            )
        }
    }
}