package com.dynast.replycompose.data.email

import androidx.annotation.DrawableRes

data class EmailAttachment(
    @DrawableRes val resId: Int,
    val contentDesc: String
)
