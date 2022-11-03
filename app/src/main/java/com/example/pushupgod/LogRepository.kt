package com.example.pushupgod

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roomdatabase.PushupLog
import kotlinx.coroutines.*
import kotlin.math.log

class LogRepository(private val logDao: PushupLogDao) {

    val allLogs: LiveData<List<PushupLog>> = logDao.getAllLogs()
    val searchResults = MutableLiveData<List<PushupLog>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertLog(newLog: PushupLog) {
        coroutineScope.launch(Dispatchers.IO) {
            logDao.insertLog(newLog)
        }
    }

    fun deleteLog(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            logDao.deleteLog(name)
        }
    }

    fun findLog(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name: String): Deferred<List<PushupLog>> =
        coroutineScope.async(Dispatchers.IO) {
            return@async logDao.findLog(name)
        }
}