package com.example.pushupgod.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pushupgod.PushupLogDao
import kotlinx.coroutines.*

class LogRepository(private val logDao: PushupLogDao) {

    val allLogs: LiveData<List<PushupLog>> = logDao.getAllLogs()
    val selectedLogs = MutableLiveData<List<PushupLog>>()
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
            selectedLogs.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name: String): Deferred<List<PushupLog>> =
        coroutineScope.async(Dispatchers.IO) {
            return@async logDao.findLog(name)
        }

    fun SortLogs(SortOrder: String){
        coroutineScope.launch(Dispatchers.IO) {
            logDao.sortLogs()
        }
    }
}