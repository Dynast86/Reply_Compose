package com.dynast.replycompose.data.search

import androidx.annotation.DrawableRes

data class SearchSuggestion(
    @DrawableRes val iconResId: Int,
    val title: String,
    val subtitle: String
)