package com.example.jiangpei.filedemo201811

import android.app.Activity
import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    val filename = "test.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener() {
            val s = editText.text.toString()
            val fos = openFileOutput(filename, Context.MODE_PRIVATE)

            fos.write(s.toByteArray())

            fos.flush()
            fos.close()
        }

        button2.setOnClickListener() {
            val fis = openFileInput(filename)
            val buffer = ByteArray(fis.available())

            fis.read(buffer)
            val s = String(buffer)
            editText.setText(s)
        }
    }
}
