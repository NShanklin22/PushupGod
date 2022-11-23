package com.example.pushupgod.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pushupgod.database.MainViewModel
import com.example.pushupgod.database.PushupLog


@Composable
fun TableScreen(
    allLogs: List<PushupLog>,
    viewModel: MainViewModel
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
