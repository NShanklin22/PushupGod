package com.example.pushupgod.ui.appbar

sealed class NavRoutes(val route: String){
    object Splash: NavRoutes("splash")
    object Table : NavRoutes("table")
    object Graph : NavRoutes("graph")
    object Overview : NavRoutes("overview")
    object NewEntry: NavRoutes("newEntry")
}
