package com.example.assignment.Utility
import android.content.Context
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object LocalLogger {

    private const val LOG_FILE_NAME = "app_logs.txt"

    fun log(context: Context, tag: String, message: String) {
        val timeStamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val logMessage = "$timeStamp [$tag]: $message\n"

        try {
            val logFile = File(context.filesDir, LOG_FILE_NAME)
            val writer = FileWriter(logFile, true) // `true` for appending
            writer.append(logMessage)
            writer.flush()
            writer.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getLogFile(context: Context): File {
        return File(context.filesDir, LOG_FILE_NAME)
    }

    fun clearLogs(context: Context) {
        getLogFile(context).delete()
    }
}
