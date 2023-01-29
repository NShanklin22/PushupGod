package com.example.pushupgod.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pushupgod.database.MainViewModel
import com.example.pushupgod.database.PushupLog
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TableScreen(
    allLogs: List<PushupLog>,
    viewModel: MainViewModel
){
    // All items placed inside of a column
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Set todays date as a string
        val todayDate = SimpleDateFormat("MM/dd/yyyy", Locale.US).format(Date()).toString()

        // Call the findLog function with todays date to get all logs from today
        viewModel.findLog(todayDate)

        //TODO write what this variable is actually doing
        val selectedLogs by viewModel.selectedLogs.observeAsState(listOf())

        // Define all logs TODO what is this being used for?
        val allLogs by viewModel.allLogs.observeAsState(listOf())

        // Defines the table head depending on what day is selected
        var head1 = if(viewModel.dailySelected) "Entry #" else "Date"

        // Adding a data selector to the top of the table
        DataRangeSelector(viewModel = viewModel)

        // All logs based from main activity
        if (allLogs != null) {

            // Lazy column will display several entries and provide the scroll functionality
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {




                /////////////////////////////////////
                /// MAJOR CONSTRUCTION AREA CLOSED///
                /////////////////////////////////////
                // list stores the current list o
                // f logs to be displayed, will show the daily logs or all logs
                // TODO this needs seriously revamped to select logs based on various dates
                if(viewModel.dailySelected){
                    val list = selectedLogs
                    val key1 ="date"
                }else{
                    val list = allLogs
                    val key1 = "id"
                }
                /////////////////////////////////////
                /// MAJOR CONSTRUCTION AREA CLOSED///
                /////////////////////////////////////

                val list = if (viewModel.dailySelected) selectedLogs else allLogs
                val key1 = if (viewModel.dailySelected) "date" else "id"

                // First item is the Title Row followed by "items" which is based a list
                item {
                    TitleRow(head1 = head1, head2 = "Date", head3 = "# Pushed")
                }

                // The list of items follows the title row
                items(list) { log ->
                    // Log row is self defined composable function
                    logRow(
                        key = if(viewModel.dailySelected){log.id.toString()}else{log.date},
                        numPushed = log.numPushed
                    )
                }
            }
        }
    }
}

// Allows selection between daily and weekly data
// TODO: Revamp this area to allow for daily, weekly, and monthly range selections
@Composable
fun DataRangeSelector(viewModel: MainViewModel){
    // Get the daily and weekly selected states from viewModel
    var dailySelected = viewModel.dailySelected
    // Set the button color based on the daily and weekly selected
    val DailyBackgroundColor = if(dailySelected) Color.White else Color.Red
    val DailyTextColor = if(dailySelected) Color.Red else Color.White
    val WeeklyBackgroundColor = if(dailySelected) Color.Red else Color.White
    val WeeklyTextColor = if(dailySelected) Color.White else Color.Red
    val todayDate = SimpleDateFormat("MM/dd/yyyy", Locale.US).format(Date()).toString()

    // Components will sit inside of a row
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "Go backwards in time"
            )
            // Daily data button
            Button(
                onClick = {
                    // Change the value of daily selected
                    viewModel.dailySelected = true
                    viewModel.findLog(todayDate)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = DailyBackgroundColor,
                    contentColor = DailyTextColor
                )
            ){
                Text("Daily")
            }

            // Weekly data button
            Button(
                onClick = {
                    // Change the value of daily selected
                    viewModel.dailySelected = false

                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = WeeklyBackgroundColor,
                    contentColor = WeeklyTextColor
                )
            ){
                Text("Weekly")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color.Red)
        ){
            Text(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                text =
                if(dailySelected){
                    "Daily Entries"
                }else{
                    "Weekly Entries"
                },
                style = TextStyle(
                    color = Color.White
                )
            )
        }
    }

}

// Title Row for top of data table
@Composable
fun TitleRow(head1: String, head2: String, head3: String) {
    Row(
        modifier = Modifier
            .background(Color.Red)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            head1,
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Text(
            head3,
            modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

// Log Row to display each data entry
@Composable
fun logRow(key:String,numPushed:Int){
    Row(
        modifier = Modifier.fillMaxWidth(),
    ){
        // First text is the key which will change depending on if daily or weekly is selected
        Text(
            key,
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center
        )

        // Number pushed
        Text(
            numPushed.toString(),
            modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center
        )
    }
}