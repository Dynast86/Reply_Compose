package com.dynast.replycompose.ui.nav

sealed class NavItem(
    val title: String? = null,
    val route: String
) {
    object Home : NavItem(title = "홈", "Home")
    object Search : NavItem(title = "검색", "Search")
    object Compose : NavItem(title = "구성", "Compose")
    object Email : NavItem(title = "이메일", "Email")

    object Close : NavItem(route = "Close")
    object Send : NavItem(route = "Send")
}
