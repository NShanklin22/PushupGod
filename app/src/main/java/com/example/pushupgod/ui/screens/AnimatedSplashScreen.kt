package com.example.pushupgod.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AnimatedSplashScreen(){
    Splash()
}

@Composable
fun Splash(){
    Box(modifier = Modifier.fillMaxSize()){
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = "Email Icon"
        )
    }
}