package com.dynast.replycompose.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dynast.replycompose.R

sealed class BottomMenu(
    @StringRes val title: Int,
    @DrawableRes val icon: Int? = null
) {
    object Forward : BottomMenu(title = R.string.menu_item_forward, icon = R.drawable.ic_forward)
    object Reply : BottomMenu(title = R.string.menu_item_reply, icon = R.drawable.ic_reply)
    object ReplyAll : BottomMenu(title = R.string.menu_item_reply_all, icon = R.drawable.ic_reply_all)
    object Archive : BottomMenu(title = R.string.menu_item_archive, icon = R.drawable.ic_archive)
    object Delete : BottomMenu(title = R.string.menu_item_delete, icon = R.drawable.ic_delete)

    object Light : BottomMenu(title = R.string.menu_dark_theme_light)
    object Dark : BottomMenu(title = R.string.menu_dark_theme_dark)
    object SystemDefault : BottomMenu(title = R.string.menu_dark_theme_system_default)
}
