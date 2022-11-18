package com.example.pushupgod

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pushupgod.ui.theme.PushupGodTheme
import com.example.pushupgod.database.MainViewModel
import com.example.pushupgod.database.PushupLog
import com.example.pushupgod.ui.appbar.AppTopBar
import com.example.pushupgod.ui.appbar.NavRoutes
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
                    // Place the top app bar and main section into a column
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Row for the top app bar
                        Row {
                            AppTopBar({})
                        }
                        // Row that holds the main screen
                        Row() {
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
                                screenSetup(viewModel = viewModel)
                            }
                        }
                        Row() {
                            
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    val NavController = rememberNavController()
}

@Composable
fun NavigationHost(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ){
        composable(NavRoutes.Home.route){
            com.example.pushupgod.ui.screens.MainScreen()
        }
    }
}


@Composable
fun screenSetup(viewModel: MainViewModel){

    val allLogs by viewModel.allLogs.observeAsState(listOf())
    val searchResults by viewModel.searchResults.observeAsState(listOf())

    Scaffold(modifier = Modifier.fillMaxSize()) {
        mainScreen(allLogs = allLogs, viewModel = viewModel)

    }
}

@Composable
fun mainScreen(
    allLogs: List<PushupLog>,
    viewModel: MainViewModel
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        dataEntry(viewModel = viewModel)

        if (allLogs != null) {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                val list = allLogs

                item {
                    TitleRow(head1 = "ID", head2 = "Date", head3 = "# Pushed")
                }

                items(list) { log ->
                    logRow(
                        id = log.id, date = log.date,
                        numPushed = log.numPushed
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController){
    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach{ navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                          navController.navigate(navItem.route){
                              popUpTo(navController.graph.findStartDestination().id){
                                  saveState = true
                              }
                              launchSingleTop = true
                              restoreState = true
                          }
                },
                icon = {
                    Icon(imageVector = navItem.image, contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title)
                },
            )
        }
    }
}


@Composable
fun dataEntry(viewModel: MainViewModel){
    var date by remember { mutableStateOf("") }
    var numPushed by remember { mutableStateOf("") }

    val onDateChange = { text : String ->
        date = text
    }

    val onNumPushedChange = { text : String ->
        numPushed = text
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // # Pushed Data Entry
        CustomTextField(
            title = "# Pushed",
            textState = numPushed,
            onTextChange = onNumPushedChange,
            keyboardType = KeyboardType.Number,
        )

        // Submit Button
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,
                contentColor = Color.White
            ) ,
            onClick = {
                if(numPushed.isNotEmpty()){
                    viewModel.insertlog(
                        PushupLog(
                            date,
                            numPushed.toInt()
                        )
                    )
                }
            }
        ) {
            Text(text = "Enter Data")
        }

    }
}

@Composable
fun logRow(id:Int,date:String,numPushed:Int){
    Row(
        modifier = Modifier.fillMaxWidth(),

    ){
        Text(
            id.toString(),
            modifier = Modifier
            .weight(0.1f),
            textAlign = TextAlign.Center
            )
        Text(
            numPushed.toString(),
            modifier = Modifier.weight(0.4f),
            textAlign = TextAlign.Center
            )
    }
}

@Composable
fun TitleRow(head1: String, head2: String, head3: String) {
    Row(
        modifier = Modifier
            .background(Color.Red)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            head2,
            modifier = Modifier
                .weight(0.1f),
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Text(
            head3,
            modifier = Modifier.weight(0.4f),
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

@Composable
fun CustomTextField(
    title: String,
    textState: String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Red,
            unfocusedBorderColor = Color.White,
            textColor = Color.Black,
            focusedLabelColor = Color.Red
            ),
        singleLine = true,
        label = { Text(title)},
        modifier = Modifier.padding(10.dp),
        textStyle = TextStyle(fontWeight = FontWeight.Bold,
            fontSize = 30.sp)
    )
}


class MainViewModelFactory(val application: Application):
        ViewModelProvider.Factory{
            override fun <T : ViewModel> create (modelClass: Class<T>): T {
                return MainViewModel(application) as T
            }
        }