package com.dynast.replycompose.temp.compose

import androidx.annotation.DrawableRes
import com.dynast.replycompose.R

data class Account(
    val id: Long,
    val uid: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val altEmail: String,
    @DrawableRes val avatar: Int,
    var isCurrentAccount: Boolean = false
) {
    val fullName: String = "$firstName $lastName"
    @DrawableRes
    val checkedIcon: Int = if (isCurrentAccount) R.drawable.ic_done else 0
}
