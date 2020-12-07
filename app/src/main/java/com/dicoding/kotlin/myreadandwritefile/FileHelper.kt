package com.dicoding.kotlin.myreadandwritefile

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.OutputStreamWriter

internal object FileHelper {

    private val TAG = FileHelper::class.java.name

    fun writeToFile(fileModel: FileModel, context: Context) {
        try {
            val outputStreamWriter = OutputStreamWriter(context.openFileOutput(fileModel.toString(), Context.MODE_PRIVATE))
            outputStreamWriter.write(fileModel.data.toString())
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.d(TAG, "File write failed: ", e)
        }
    }

    fun readFromFile(context: Context, filename: String): FileModel {
        val fileModel = FileModel()

        try {
            val inputStream = context.openFileInput(filename)

            if (inputStream != null) {
                val receiveString = inputStream.bufferedReader().use(BufferedReader::readText)
                inputStream.close()
                fileModel.data = receiveString
                fileModel.filename = filename
            }
        } catch (e: FileNotFoundException) {
            Log.d(TAG, "File not found: ", e)
        } catch (e: IOException) {
            Log.d(TAG, "Cannot read file: ", e)
        }

        return fileModel
    }
}