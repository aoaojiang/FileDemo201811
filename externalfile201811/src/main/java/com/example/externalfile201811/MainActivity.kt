package com.example.externalfile201811

import android.app.Activity
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sdDir = Environment.getExternalStorageDirectory()
        val sdFile = File(sdDir, "demo.txt")

        button.setOnClickListener() {
            val dir = Environment.getExternalStorageDirectory().absolutePath

            textView.text = dir
            val s = editText.text.toString()

            if (sdDir.exists() && sdDir.canWrite()) {
                sdFile.createNewFile()

                if (sdFile.exists() && sdFile.canWrite()) {
                    val fos = FileOutputStream(sdFile)
                    fos.write(s.toByteArray())

                    fos.flush()
                    fos.close()
                }
            }
        }

        button2.setOnClickListener() {
            val fis = FileInputStream(sdFile)
            val buffer = ByteArray(fis.available())
            fis.read(buffer)

            textView.text = String(buffer)

            fis.close()
        }

        button3.setOnClickListener() {
            val filename = sdDir.absolutePath + "/mickey.png"
            val pic = File(filename)

            if (pic.exists()) {
                val bitmap = BitmapFactory.decodeFile(filename)

                imageView.setImageBitmap(bitmap)
            }

        }
    }
}
