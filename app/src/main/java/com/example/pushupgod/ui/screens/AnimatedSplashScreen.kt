package com.example.pushupgod.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pushupgod.ui.theme.SynthwaveOrange
import com.example.pushupgod.ui.theme.SynthwaveYellow

@Composable
fun AnimatedSplashScreen(){
    Splash()
}

@Composable
fun Splash(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SynthwaveOrange)
    ){
        Icon(
            modifier = Modifier
                .size(120.dp),
            imageVector = Icons.Default.Email,
            contentDescription = "Email Icon",

        )
    }
}