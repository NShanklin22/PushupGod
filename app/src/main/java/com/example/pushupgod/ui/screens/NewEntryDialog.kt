package com.example.pushupgod.ui.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.pushupgod.database.LogViewModel
import com.example.pushupgod.database.PushupLog
import java.text.SimpleDateFormat
import java.util.*

// Primary composable called by MainActivity
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewEntryDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    viewModel: LogViewModel
){
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Card(
            elevation = 5.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.fillMaxWidth(fraction = .95f)
        ){
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ){
                dataEntry(viewModel = viewModel, onConfirm())
            }
        }
    }

}

// Composable which handles gathering and submission of data
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun dataEntry(viewModel: LogViewModel, onConfirm: Unit){
    val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.US)
    val time: String = sdf.format(Date())
    var date by remember { mutableStateOf(sdf.format(Date()).toString()) }
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
        val context = LocalContext.current
        // Data entry date
        CustomTextField(
            title = "Date",
            textState = date,
            onTextChange = onDateChange,
            keyboardType = KeyboardType.Text,
        )

        // Data Entry # Pushed
        CustomTextField(
            title = "# Pushed",
            textState = numPushed,
            onTextChange = onNumPushedChange,
            keyboardType = KeyboardType.Number,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            // Submit Button
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                ) ,
                onClick = {
                    // First check that the user has entered a value, if not display a toast
                    if(numPushed.isNotEmpty()){
                        // Insert new entry to the table
                        viewModel.insertlog(
                            PushupLog(
                                date,
                                numPushed.toInt()
                            )
                        )
                        // Clear num pushed field
                        numPushed = ""
                        viewModel.NewEntrySelected = false
                    }else{
                        Toast.makeText(context,"Please entry a value", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(text = "Enter Data")
            }
            // Cancel Button
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                ) ,
                onClick = {
                    viewModel.NewEntrySelected = false
                }
            ) {
                Text(text = "Cancel")
            }


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
