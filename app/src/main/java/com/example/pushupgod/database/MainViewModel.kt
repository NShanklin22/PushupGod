package com.example.pushupgod.database

import android.app.Application
import android.text.BoringLayout
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(application: Application) : ViewModel() {

    // allLogs provides all pushup logs as a List of PushupLog Objects
    val allLogs: LiveData<List<PushupLog>>
    // repository is an instance of class LogRepository in system tree, hold methods for accessing data
    private val repository: LogRepository
    // search results is reutrned by the repository
    val selectedLogs: MutableLiveData<List<PushupLog>>
    var dailySelected by mutableStateOf(true)
    // Variable to note if in the new entry page
    var NewEntrySelected by mutableStateOf(false)


    init {
        val logDb = PushupLogDatabase.getInstance(application)
        val logDao = logDb.productDao()
        repository = LogRepository(logDao)
        allLogs = repository.allLogs
        selectedLogs = repository.selectedLogs
    }

    fun onDismissDialog(){

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

    fun sortLogs(SortOrder: String){

    }
}