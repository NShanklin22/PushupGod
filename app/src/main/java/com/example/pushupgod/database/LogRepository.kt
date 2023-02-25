package com.example.pushupgod.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pushupgod.PushupLogDao
import kotlinx.coroutines.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.log

@RequiresApi(Build.VERSION_CODES.O)
class LogRepository(private val logDao: PushupLogDao) {

    // Listed logs are all logs that will be displayed on the table view
    var listedLogs = MutableLiveData<List<PushupLog>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    // Get the current date
    @RequiresApi(Build.VERSION_CODES.O)
    val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/YY")
    @RequiresApi(Build.VERSION_CODES.O)
    val TodaysDate = LocalDate.now()
    @RequiresApi(Build.VERSION_CODES.O)
    var formattedDate = TodaysDate.format(dateFormatter)


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

    //Find a log from the database by date
    fun findLog(date: String) {
        coroutineScope.launch(Dispatchers.Main) {
            listedLogs.postValue(asyncFind(date).await())
        }
    }
    private fun asyncFind(date: String): Deferred<List<PushupLog>> =
        coroutineScope.async(Dispatchers.IO) {
            return@async logDao.findLog(date)
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