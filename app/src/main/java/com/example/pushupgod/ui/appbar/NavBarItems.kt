package com.example.pushupgod

import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Face
import com.example.pushupgod.ui.appbar.BarItem

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = "home"
        ),
        BarItem(
            title = "Table",
            image = Icons.Filled.Face,
            route = "table"
        ),
        BarItem(
            title = "Graph",
            image = Icons.Filled.Favorite,
            route = "graph"
        )
    )
}