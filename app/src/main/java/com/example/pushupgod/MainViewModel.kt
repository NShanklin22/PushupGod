package com.example.pushupgods

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pushupgod.LogRepository
import com.example.pushupgod.PushupLogDatabase
import com.example.roomdatabase.PushupLog

class MainViewModel(application: Application) : ViewModel() {

    val allLogs: LiveData<List<PushupLog>>
    private val repository: LogRepository
    val searchResults: MutableLiveData<List<PushupLog>>

    init {
        val productDb = PushupLogDatabase.getInstance(application)
        val productDao = productDb.productDao()
        repository = LogRepository(productDao)

        allLogs = repository.allLogs
        searchResults = repository.searchResults
    }

    fun insertProduct(pushupLog: PushupLog) {
        repository.insertLog(pushupLog)
    }

    fun findProduct(name: String) {
        repository.findLog(name)
    }

    fun deleteProduct(name: String) {
        repository.deleteLog(name)
    }
}