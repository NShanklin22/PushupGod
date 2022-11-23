package com.example.pushupgod

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pushupgod.ui.theme.PushupGodTheme
import com.example.pushupgods.MainViewModel
import com.example.roomdatabase.PushupLog

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PushupGodTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

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
            }
        }
    }
}

@Composable
fun screenSetup(viewModel: MainViewModel){

    val allLogs by viewModel.allLogs.observeAsState(listOf())
    val searchResults by viewModel.searchResults.observeAsState(listOf())

    mainScreen(allLogs = allLogs, viewModel = viewModel)
}

@Composable
fun mainScreen(
    allLogs: List<PushupLog>,
    viewModel: MainViewModel
){
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        dataEntry()

        if (allLogs != null) {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
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
fun dataEntry(){
    var date by remember { mutableStateOf("") }
    var numPushed by remember { mutableStateOf("") }

    val onDateChange = { text : String ->
        date = text
    }

    val onNumPushedChange = { text : String ->
        numPushed = text
    }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        CustomTextField(
            title = "Date",
            textState = date,
            onTextChange = onDateChange,
            keyboardType = KeyboardType.Text
        )

        CustomTextField(
            title = "# Pushed",
            textState = numPushed,
            onTextChange = onNumPushedChange,
            keyboardType = KeyboardType.Number
        )
    }
}

@Composable
fun logRow(id:Int,date:String,numPushed:Int){
    Row(
        modifier = Modifier.fillMaxWidth()
    ){
         Text(id.toString(), modifier = Modifier
             .weight(0.1f))
         Text(date, modifier = Modifier.weight(0.2f))
         Text(numPushed.toString(), modifier = Modifier.weight(0.2f))
    }
}

@Composable
fun TitleRow(head1: String, head2: String, head3: String) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(head1, color = Color.White,
            modifier = Modifier
                .weight(0.1f))
        Text(head2, color = Color.White,
            modifier = Modifier
                .weight(0.2f))
        Text(head3, color = Color.White,
            modifier = Modifier.weight(0.2f))
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