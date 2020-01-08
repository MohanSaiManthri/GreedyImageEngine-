package com.mohansaimanthri.greedyimageloader.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Process
import java.io.BufferedInputStream
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.net.URL
import java.util.concurrent.ThreadFactory

object Utils {
    internal class ImageThreadFactory : ThreadFactory {
        override fun newThread(r: Runnable): Thread {
            return Thread(r).apply {
                this.name = "ImageLoader Thread"
                this.priority = Process.THREAD_PRIORITY_BACKGROUND
            }
        }
    }

    fun scaleBitmapForLoad(bitmap: Bitmap, screenWidth: Int, screenHeight: Int): Bitmap? {
        if (screenHeight == 0 || screenWidth == 0) return bitmap

        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, stream)
        val inputStream = BufferedInputStream(ByteArrayInputStream(stream.toByteArray()))
        return scaleBitmap(inputStream, screenWidth, screenHeight)
    }

   /* fun downloadBitmapFromURL(url: String): Bitmap? {
        val url = URL(url)
        val inputStream = BufferedInputStream(url.openConnection().getInputStream())
        return scaleBitmap(inputStream, ImageLoader.screenWidth, ImageLoader.screenHeight)
    }*/

    fun scaleBitmap(
        inputStream: BufferedInputStream,
        screenWidth: Int,
        screenHeight: Int
    ): Bitmap? {
        return BitmapFactory.Options().run {
            inputStream.mark(inputStream.available())
            inJustDecodeBounds = true
            BitmapFactory.decodeStream(inputStream, null, this)
            inSampleSize = calculateInSampleSize(this, screenWidth, screenHeight)
            inJustDecodeBounds = false
            inputStream.reset()
            BitmapFactory.decodeStream(inputStream, null, this)
        }
    }


    // From Developer Site
    private fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): Int {

        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) inSampleSize *= 2
        }

        return inSampleSize
    }

}