package com.example.pushupgod.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OverviewScreen(){
    // All widgets place inside of this column
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text is temporary
        for (i in 1..3) {
            TextWindow(titleText = "Nate", dataText = i.toString())
        }
    }
}

@Composable
fun TextWindow(titleText:String, dataText:String){
    Box(contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Text(titleText)
            Text(dataText)
        }
    }
}

@Composable
@Preview
fun previewOverviewScreen(){
    SettingsScreen()
}