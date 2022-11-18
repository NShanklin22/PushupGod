package com.example.pushupgod.ui.appbar

sealed class NavRoutes(val route: String){
    object Home : NavRoutes("home")
    object Table : NavRoutes("table")
    object Graph : NavRoutes("graph")
}
