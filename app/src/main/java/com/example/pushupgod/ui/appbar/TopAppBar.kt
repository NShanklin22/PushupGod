package com.example.pushupgod.ui.appbar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun AppTopBar(
    onAddClicked: () -> Unit
){
    TopAppBar(
        title = {
            Text(
                text = "Pushup GOD",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp)
        },
        actions = {
            AppBarActions(
                onAddClicked = onAddClicked
            )
        },
        backgroundColor = Color.Red
    )
}

@Composable
fun AppBarActions(
    onAddClicked:()-> Unit
    ){
    IconButton(
        onClick = { onAddClicked() }
    ){
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add New Entry"
        )
    }
}


@Composable
@Preview
fun previewAppTopBar(){
    AppTopBar(onAddClicked = {})
}