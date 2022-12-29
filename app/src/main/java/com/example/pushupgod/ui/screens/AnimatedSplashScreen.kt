package com.example.pushupgod.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.pushupgod.R
import com.example.pushupgod.ui.appbar.NavRoutes
import kotlinx.coroutines.delay

// The splash screen will open when the app is first launches
@Composable
fun AnimatedSplashScreen(navController: NavHostController){
    var startAnimation by remember{ mutableStateOf(false) }
    val alphaAnimation = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 3000)
    )

    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(NavRoutes.Table.route)
    }
    Splash(alphaAnimation.value)
}

@Composable
fun Splash(alpha: Float){
    Box(modifier =
    Modifier
        .fillMaxSize()
        .alpha(alpha)
        .background(Color.Black),
        contentAlignment = Alignment.Center

    ){
        Image(painter = painterResource(id = R.drawable.startscreen), contentDescription = "Start up image of swole god")
    }
}