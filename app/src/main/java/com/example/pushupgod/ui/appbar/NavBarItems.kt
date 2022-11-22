package com.example.pushupgod

import android.graphics.drawable.Icon
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.pushupgod.ui.appbar.BarItem

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Table",
            image = Icons.Filled.List,
            route = "table"
        ),
        BarItem(
            title = "Graph",
            image = Icons.Filled.KeyboardArrowUp,
            route = "graph"
        )
    )
}