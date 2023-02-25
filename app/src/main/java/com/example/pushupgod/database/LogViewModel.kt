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
class LogViewModel(application: Application) : ViewModel() {
    // repository is an instance of class LogRepository in system tree, hold methods for accessing data
    val logDb = PushupLogDatabase.getInstance(application)
    val logDao = logDb.logDao()
    val repository = LogRepository(logDao)

    val listedLogs by mutableStateOf(repository.listedLogs)

    // Show entries from today
    var dailySelected by mutableStateOf(true)
    // Show entries from this week
    var weeklySelected by mutableStateOf(false )
    // Show entries from this month
    var monthlySelected by mutableStateOf(false)

    // Variable to note if in the new entry page
    var NewEntrySelected by mutableStateOf(false)

    // Variable to store the active week
    val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
    val TodaysDate = LocalDate.now()
    var activeDay by mutableStateOf(TodaysDate)
    var activeWeek by mutableStateOf(1)
    var activeMonth by mutableStateOf(1)

    init{
        repository.findLog(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))
    }

    fun insertlog(pushupLog: PushupLog) {
        repository.insertLog(pushupLog)
    }

    fun findLog(SelectedDateRange: String) {
        repository.findLog(SelectedDateRange)
    }

//    fun deleteLog(name: String) {
//        repository.deleteLog(name)
//    }
//
//    fun sortLogs() {
//        repository.sortLogs()
//    }
}