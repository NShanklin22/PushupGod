package com.example.pushupgod.database

import android.app.Application
import android.os.Build
import android.text.BoringLayout
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel(application: Application) : ViewModel() {

    // repository is an instance of class LogRepository in system tree, hold methods for accessing data
    private val repository: LogRepository

    // Variables that determine what range of data is being displayed
    // Show entries from today
    var dailySelected by mutableStateOf(true)
    // Show entries from this week
    var weeklySelected by mutableStateOf(false )
    // Show entries from this month
    var monthlySelected by mutableStateOf(false)

    // Variable to note if in the new entry page
    var NewEntrySelected by mutableStateOf(false)

    // Variable to store the active week
    val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy")
    val TodaysDate = LocalDate.now()
    var activeDay by mutableStateOf(TodaysDate)
    var activeWeek by mutableStateOf(1)
    var activeMonth by mutableStateOf(1)

    // listed logs are what are displayed on the main
    lateinit var listedLogs : MutableLiveData<List<PushupLog>>

    // Variable to store the active day

    init {
        val logDb = PushupLogDatabase.getInstance(application)
        val logDao = logDb.productDao()
        repository = LogRepository(logDao)
        listedLogs = repository.listedLogs
    }

    fun insertlog(pushupLog: PushupLog) {
        repository.insertLog(pushupLog)
    }

    fun findLog(SelectedDateRange: String) {
        repository.findLog(SelectedDateRange)
    }

    fun deleteLog(name: String) {
        repository.deleteLog(name)
    }

    fun sortLogs() {
        repository.sortLogs()
    }

    // Function with Increment/Decrement the active day (for daily view)
    fun changeActiveDay(direction: Int){
        if(direction == 1){
            activeDay = activeDay.plusDays(1)
        }else{
            activeDay = activeDay.minusDays(1)
        }
    }

    // Function with Increment/Decrement the active week (for weekly view)
    fun changeActiveWeek(direction: Int){
        if(direction == 1){

        }else{

        }
    }

    // Function with Increment/Decrement the active month (for monthly view)
    fun changeActiveMonth(direction: Int){
        if(direction == 1){

        }else{

        }
    }

}