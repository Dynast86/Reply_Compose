package com.dynast.replycompose.ui.email

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EmailAttachmentWidget(
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth()) {
        repeat(20) {
            Text(it.toString())
        }
    }
}