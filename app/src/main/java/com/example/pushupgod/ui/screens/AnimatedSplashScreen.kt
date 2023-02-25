package com.example.pushupgod.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.pushupgod.database.LogViewModel
import com.example.pushupgod.ui.appbar.NavRoutes
import kotlinx.coroutines.delay

// The splash screen will open when the app is first launches
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AnimatedSplashScreen(navController: NavHostController, viewModel:LogViewModel){
    var startAnimation by remember{ mutableStateOf(false) }
    val alphaAnimation = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 3000)
    )

    LaunchedEffect(key1 = true){
        startAnimation = true
        val listedLogs = viewModel.listedLogs
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
    }
}