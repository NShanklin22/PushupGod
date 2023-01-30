package com.example.pushupgod.ui.appbar

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.pushupgod.NavBarItems
import com.example.pushupgod.database.MainViewModel
import com.example.pushupgod.ui.screens.NewEntryDialog

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TopNavigationBar(
    navController: NavHostController,
    viewModel: MainViewModel
){

    // showMenu variable will determine if "more options" will display the additional options
    var showMenu by remember{ mutableStateOf(false) }
    var sortMenu by remember{ mutableStateOf(false) }
    var context = LocalContext.current

    TopAppBar(
        title = {
            Text(
                textAlign = TextAlign.Start,
                text = "Pushup GOD",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp)
        },
        actions = {
            // Icon button will show the drop down menu when clicked
            IconButton(
                onClick = {
                    showMenu = !showMenu
                }
            ){
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "show options"
                )
            }
            // Drow down menu will hold the additional menu options
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false}
            ) {
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Text("Settings")
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Text("About")
                }
            }

        },
        backgroundColor = Color.Red
    )
}

@Composable
fun optionMenu(){

}

@Composable
fun AppBarActions(
    OnSortClicked:()-> Unit,
    ){
    IconButton(
        onClick = {

        }
    ){
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Add New Entry"
        )
    }
}