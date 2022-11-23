package com.example.pushupgod.database

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(application: Application) : ViewModel() {

    val allLogs: LiveData<List<PushupLog>>
    private val repository: LogRepository
    val searchResults: MutableLiveData<List<PushupLog>>

    init {
        val logDb = PushupLogDatabase.getInstance(application)
        val logDao = logDb.productDao()
        repository = LogRepository(logDao)

        allLogs = repository.allLogs
        searchResults = repository.searchResults
    }

    fun insertlog(pushupLog: PushupLog) {
        repository.insertLog(pushupLog)
    }

    fun findLog(name: String) {
        repository.findLog(name)
    }

    fun deleteLog(name: String) {
        repository.deleteLog(name)
    }
}