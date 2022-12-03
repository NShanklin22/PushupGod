package com.example.pushupgod.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GraphScreen(){
    // All widgets place inside of this column
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Row for holding page description
        Row(
            modifier = Modifier.fillMaxWidth()
        ){

        }
        // Text is temporary
        Text(text = "This is the graph screen a beautiful graph will go here")
    }
}