package com.example.jc_for_img_comp_otp_field.image_compress

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FileManager(
    private val context: Context
) {


    suspend fun saveImage(
        contentUri: Uri,
        fileName: String
    ) {
        withContext(Dispatchers.IO) {
            context
                .contentResolver
                .openInputStream(contentUri)
                ?.use { inputStream ->
                    context
                        .openFileOutput(fileName, Context.MODE_PRIVATE)
                        .use { outputSteam ->
                            inputStream.copyTo(outputSteam)
                        }
                }
        }
    }

    suspend fun saveImage(
        bytes: ByteArray,
        fileName: String
    ) {
        withContext(Dispatchers.IO) {
            context
                .openFileOutput(fileName, Context.MODE_PRIVATE)
                .use { outputStream ->
                    outputStream.write(bytes)
                }
        }
    }
}