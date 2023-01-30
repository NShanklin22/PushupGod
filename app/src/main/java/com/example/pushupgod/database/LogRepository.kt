package com.example.pushupgod.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pushupgod.PushupLogDao
import kotlinx.coroutines.*
import kotlin.math.log

class LogRepository(private val logDao: PushupLogDao) {

    // Listed logs are all logs that will be displayed on the table view
    var listedLogs = MutableLiveData<List<PushupLog>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    // Insert a new log into the database
    fun insertLog(newLog: PushupLog) {
        coroutineScope.launch(Dispatchers.IO) {
            logDao.insertLog(newLog)
        }
    }

    //Delete a log from the database
    fun deleteLog(ID: String) {
        coroutineScope.launch(Dispatchers.IO) {
            logDao.deleteLog(ID)
        }
    }

    //Find a log from the database by name
    fun findLog(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            listedLogs.postValue(asyncFind(name).await())
        }
    }
    private fun asyncFind(name: String): Deferred<List<PushupLog>> =
        coroutineScope.async(Dispatchers.IO) {
            return@async logDao.findLog(name)
        }


    // Find all logs between the start date and end date
    fun getLogsBetweenDates(StartDate: String, EndDate:String){
        logDao.getLogsBetweenDates(StartDate,EndDate)
    }

    // Sort the logs
    fun sortLogs(){
        coroutineScope.launch(Dispatchers.IO) {
            listedLogs.postValue(logDao.sortLogsByDateDesc())
        }
    }
}