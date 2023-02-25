package com.example.pushupgod

import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pushupgod.ui.theme.PushupGodTheme
import com.example.pushupgod.database.LogViewModel
import com.example.pushupgod.ui.appbar.BottomNavigationBar
import com.example.pushupgod.ui.appbar.NavRoutes
import com.example.pushupgod.ui.appbar.TopNavigationBar
import com.example.pushupgod.ui.screens.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // The theme is defined inside of Theme.kt
            PushupGodTheme {
                // In this section we are defining the system ui controller and using it to make the
                // status bar (top most system bar) red instead of purple
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Color.Red,
                        darkIcons = false
                    )
                }
                // Surface for the entire screen, everything else will sit on this surface
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
                            // Define the view model which will hold the key data and functions used in the rest of the application
                            val viewModel: LogViewModel = viewModel(
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenSetup(viewModel: LogViewModel){

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
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.NewEntrySelected = true
                }
            ) {
                // Add an icon to the FAB using the Icon component
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add a new entry")
            }
        },
        content = { NavigationHost(navController = navController, viewModel)},
        bottomBar = { BottomNavigationBar(navController = navController) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationHost(navController: NavHostController, viewModel: LogViewModel){

    // Define the allLogs to be used by the various screens
    val allLogs by viewModel.listedLogs.observeAsState(listOf())

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash.route,
    ){
        composable(NavRoutes.Splash.route){
            AnimatedSplashScreen(navController, viewModel)
        }

        composable(NavRoutes.Overview.route){
            OverviewScreen()
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
            @RequiresApi(Build.VERSION_CODES.O)
            override fun <T : ViewModel> create (modelClass: Class<T>): T {
                return LogViewModel(application) as T
            }
        }