package com.dynast.replycompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun EmailFormWidget(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 4.dp, end = 4.dp, top = 8.dp)
            .clip(shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
            .background(color = MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
    ) {
        content()
    }
}