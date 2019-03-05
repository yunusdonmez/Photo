package com.genesis.randomphoto.framework.ImageDownloader

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import com.genesis.randomphoto.dto.FabSingletonItem
import java.io.File
import java.io.FileOutputStream

object ImageDownload {
    fun saveImage(image: Bitmap, context: Context): String {
        var saveImagePath = ""
        val imageFileName = "JPEG_" + "${FabSingletonItem.selected}" + ".jpg"
        val storageDir =
            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString())
        var success = true
        if (!storageDir.exists())
            success = storageDir.mkdirs()
        if (success) {
            val imageFile = File(storageDir, imageFileName)
            saveImagePath = imageFile.absolutePath
            try {
                val fOut = FileOutputStream(imageFile)
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
                fOut.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            // Add the image to the system gallery
            galleryAddPic(saveImagePath, context)
            Toast.makeText(context, "IMAGE SAVED", Toast.LENGTH_LONG).show()
        }
        return saveImagePath
    }

    fun galleryAddPic(imagePath: String, context: Context) {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val f = File(imagePath)
        val contentUri = Uri.fromFile(f)
        mediaScanIntent.data = contentUri
        context.sendBroadcast(mediaScanIntent)
    }
}