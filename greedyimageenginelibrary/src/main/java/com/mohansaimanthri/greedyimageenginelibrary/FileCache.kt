package com.mohansaimanthri.greedyimageloader.utils

import android.content.Context
import java.io.File

class FileCache(context: Context) {
    private val cacheDir: File = File(context.filesDir, "TempImages")
    fun getFile(url: String): File {
        val filename = url.hashCode().toString()
        return File(cacheDir, filename)
    }

    fun clear() {
        val files = cacheDir.listFiles() ?: return
        for (f in files) f.delete()
    }

    init { //Find the dir to save cached images
//if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
        //else
//    cacheDir=context.getCacheDir();
        if (!cacheDir.exists()) cacheDir.mkdirs()
    }
}