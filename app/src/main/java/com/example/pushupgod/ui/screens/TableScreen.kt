package com.example.pushupgod.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pushupgod.database.LogViewModel
import com.example.pushupgod.database.PushupLog
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TableScreen(
    allLogs: List<PushupLog>,
    viewModel: LogViewModel
){
    // All items placed inside of a column
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // Top data range select allows user to navigate between different dates for data
        DataRangeSelector(viewModel = viewModel)

        // Data Table view holds code for the table UI
        DataTable(viewModel = viewModel)

    }
}

// Allows selection between daily and weekly data
@RequiresApi(Build.VERSION_CODES.O)
// TODO: Revamp this area to allow for daily, weekly, and monthly range selections
@Composable
fun DataRangeSelector(viewModel: LogViewModel){

    // Date formatter for date strings
    val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/YYYY")

    val weekStart = SimpleDateFormat("MM/dd/yyyy", Locale.US).format(Date()).toString()
    val weekEnd = SimpleDateFormat("MM/dd/yyyy", Locale.US).format(Date()).toString()

    // Get the context for the toast
    val context = LocalContext.current
    // Components will sit inside of a row
    Column() {

        // First row has the daily/weekly entries selector
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ){

            // Back arrow to navigate backwards through time
            Button(
                modifier = Modifier.padding(horizontal = 5.dp),
                onClick = {
                }
            ){
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Navigate backward in ~time")
            }

            // Daily data button
            Button(
                modifier = Modifier.padding(horizontal = 5.dp),
                onClick = {
                    // Change the value of daily selected
                    viewModel.dailySelected = true
                    viewModel.weeklySelected = false
                    viewModel.monthlySelected = false
                    viewModel.findLog(viewModel.activeDay.toString())
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (viewModel.dailySelected) Color.White else Color.Red,
                    contentColor = if (viewModel.dailySelected) Color.Red else Color.White,
                )
            ){
                Text("Daily")
            }

            // Weekly data button
            Button(
                modifier = Modifier.padding(horizontal = 5.dp),
                onClick = {
                    // Change the value of daily selected
                    viewModel.dailySelected = false
                    viewModel.weeklySelected = true
                    viewModel.monthlySelected = false
                    viewModel.findLog(viewModel.activeDay.toString())
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (viewModel.weeklySelected) Color.White else Color.Red,
                    contentColor = if (viewModel.weeklySelected) Color.Red else Color.White,
                )
            ){
                Text("Weekly")
            }

            // Monthly data button
            Button(
                modifier = Modifier.padding(horizontal = 5.dp),
                onClick = {
                    // Change the value of daily selected
                    viewModel.dailySelected = false
                    viewModel.weeklySelected = false
                    viewModel.monthlySelected = true
                    viewModel.findLog(viewModel.activeDay.toString())
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (viewModel.monthlySelected) Color.White else Color.Red,
                    contentColor = if (viewModel.monthlySelected) Color.Red else Color.White,
                )
            ){
                Text("Monthly")
            }

            // Button to navigate forward through time
            Button(
                modifier = Modifier.padding(horizontal = 5.dp),
                onClick = {
                }
            ){
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Navigate forward in ~time")
            }
        }

        // Second row provides text feedback on what timeframe is selected
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
        ){
            Text(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                text =
                if(viewModel.dailySelected) {
                    "Daily Entries | 01/31/23"
                } else if(viewModel.weeklySelected){
                    "Weekly Entries | 01/22/23 - 01/29/23"
                }else{
                    "Monthly Entries | February"
                },
                style = TextStyle(
                    color = Color.White
                )
            )
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DataTable(viewModel: LogViewModel){

    val listedLogs:List<PushupLog> = remember {
        viewModel.listedLogs.value!!
    }

    // Set the table head depending on where daily values button is selected
    var TableHead1 =
        if(viewModel.dailySelected){
            "Time"
        } else if (viewModel.weeklySelected){
            "Date"
        }else{
            "Date"
        }

    TitleRow(head1 = TableHead1, head2 = "# Pushed")

    if (listedLogs != null) {
            // Lazy column will display all the of the entries
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
            ) {
                items(listedLogs) { log ->
                    logRow(
                        key = if (viewModel.dailySelected) {
                            log.id.toString()
                        } else {
                            log.date
                        },
                        numPushed = log.numPushed
                    )
                }
            }
    }
    else{
        Column(modifier = Modifier.padding(5.dp)) {
            Text(
                modifier = Modifier.padding(15.dp),
                text = "No logs for date selected"
            )
        }
    }
}

// Title Row for top of data table
@Composable
fun TitleRow(head1: String, head2: String) {
    Row(
        modifier = Modifier
            .padding(5.dp)
            .background(Color.Red)
            .fillMaxWidth()
    ) {
        Text(
            head1,
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Text(
            head2,
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GetDateAndTime(date: String): String{
    val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/YY")
    val TodaysDate = LocalDate.now()
    val dayOfWeek = Calendar.DAY_OF_WEEK
    val startOfWeek = TodaysDate.minus(Period.of(0,0,dayOfWeek - 1)).format(dateFormatter).toString()
    val endOfWeek = TodaysDate.plusDays((dayOfWeek - 7).toLong()).format(dateFormatter).toString()
    return "$startOfWeek to $endOfWeek"
}
