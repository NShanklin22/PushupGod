package com.example.pushupgod.ui.appbar

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.pushupgod.NavBarItems
import com.example.pushupgod.database.MainViewModel
import com.example.pushupgod.ui.screens.NewEntryDialog

@Composable
fun TopNavigationBar(
    navController: NavHostController,
    viewModel: MainViewModel
){
    TopAppBar(
        navigationIcon = {

        },
        title = {
            Text(
                text = "Pushup GOD",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp)
        },
        actions = {
            AppBarActions {
                viewModel.NewEntrySelected = true
            }
        },
        backgroundColor = Color.Red
    )
}

@Composable
fun AppBarActions(
    onAddClicked:()-> Unit,
    ){
    IconButton(
        onClick = {
            onAddClicked()
            Log.e(TAG, "AppBarActions: The button has clicked")
        }
    ){
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add New Entry"
        )
    }
}