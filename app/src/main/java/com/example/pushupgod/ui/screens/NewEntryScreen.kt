package com.example.pushupgod.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pushupgod.database.MainViewModel
import com.example.pushupgod.database.PushupLog

@Composable
fun NewEntry(viewModel: MainViewModel){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        dataEntry(viewModel = viewModel)
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

@Preview
@Composable
fun PreviewNewEntry(){
}
