package com.example.pushupgod

import android.app.Activity
import android.app.Application
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pushupgod.ui.theme.PushupGodTheme
import com.example.pushupgod.database.MainViewModel
import com.example.pushupgod.ui.appbar.BottomNavigationBar
import com.example.pushupgod.ui.appbar.NavRoutes
import com.example.pushupgod.ui.appbar.TopNavigationBar
import com.example.pushupgod.ui.screens.AnimatedSplashScreen
import com.example.pushupgod.ui.screens.GraphScreen
import com.example.pushupgod.ui.screens.NewEntryDialog
import com.example.pushupgod.ui.screens.TableScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PushupGodTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Color.Red,
                        darkIcons = false
                    )
                }
                // Surface for the entire screen
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // Row that holds the main screen
                    Row{
                        //TODO: Write out what this is used for
                        val owner = LocalViewModelStoreOwner.current

                        // Get the viewmodel
                        owner?.let {
                            val viewModel: MainViewModel = viewModel(
                                it,
                                "MainViewModel",
                                MainViewModelFactory(
                                    LocalContext.current.applicationContext as Application
                                )
                            )
                            // Call screen setup and pass view model
                            ScreenSetup(viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ScreenSetup(viewModel: MainViewModel){

    val navController = rememberNavController()
    if(viewModel.NewEntrySelected){
        NewEntryDialog(
            viewModel = viewModel,
            onConfirm = {},
            onDismiss = {}
        )
    }

    Scaffold(
        topBar = { TopNavigationBar(navController = navController, viewModel) },
        content = { NavigationHost(navController = navController, viewModel)},
        bottomBar = { BottomNavigationBar(navController = navController) }
    )
}

@Composable
fun NavigationHost(navController: NavHostController, viewModel: MainViewModel){
    val allLogs by viewModel.allLogs.observeAsState(listOf())

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash.route,
    ){
        composable(NavRoutes.Splash.route){
            AnimatedSplashScreen(navController)
        }
        composable(NavRoutes.Table.route){
            TableScreen(allLogs,viewModel)
        }
        composable(NavRoutes.Graph.route){
            GraphScreen()
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(val application: Application):
        ViewModelProvider.Factory{
            override fun <T : ViewModel> create (modelClass: Class<T>): T {
                return MainViewModel(application) as T
            }
        }