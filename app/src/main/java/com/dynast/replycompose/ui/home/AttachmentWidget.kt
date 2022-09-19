package com.dynast.replycompose.ui.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AttachmentWidget(
    modifier: Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        repeat(20) {
            Text(it.toString())
        }
    }
}