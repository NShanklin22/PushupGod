package com.example.pushupgod.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun OverviewScreen(){
    // All widgets place inside of this column
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text is temporary
        TextWindow(titleText = "PUSHED TODAY", dataText = 8.toString())
        TextWindow(titleText = "PUSHED THIS WEEK", dataText = 52.toString())
        TextWindow(titleText = "PUSHED THIS MONTH", dataText = 206.toString())
        TextWindow(titleText = "AVERAGE PUSHED", dataText = 33.2.toString())
        TextWindow(titleText = "MOST PUSHED AT ONE TIME", dataText = 999.toString())
    }
}

@Composable
fun TextWindow(titleText:String, dataText:String){
    Box(contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(12.dp)
                    .border(2.dp, MaterialTheme.colors.secondary, CircleShape)
                    .background(MaterialTheme.colors.primary, CircleShape)
                    .padding(16.dp),
                text = titleText,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    background = Color.Red
                )
                )
            Text(dataText)
        }
    }
}

@Composable
@Preview
fun previewOverviewScreen(){
    SettingsScreen()
}