package com.example.pushupgod.ui.appbar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pushupgod.database.LogViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TopNavigationBar(
    navController: NavHostController,
    viewModel: LogViewModel
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