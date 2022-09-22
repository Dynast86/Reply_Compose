package com.dynast.replycompose.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val SmallComponentCornerRadius = 24.dp
val MediumComponentCornerRadius = 0.dp
val LargeComponentCornerRadius = 12.dp

val profileImageSize = 42.dp
val listItemHeight = 56.dp

val Shapes = Shapes(
    small = RoundedCornerShape(SmallComponentCornerRadius),
    medium = RoundedCornerShape(MediumComponentCornerRadius),
    large = RoundedCornerShape(LargeComponentCornerRadius)
)