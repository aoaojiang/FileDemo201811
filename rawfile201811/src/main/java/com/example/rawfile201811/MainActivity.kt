package com.example.rawfile201811

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener() {
            val inputStream = resources.openRawResource(R.raw.test)
            val content = inputStream2String(inputStream)

            textView.text = resources.getResourceName(R.raw.test)
            editText.setText(content)
        }

        button2.setOnClickListener() {
            val inputStream = assets.open("test.txt")
            val content = inputStream2String(inputStream)

            textView.text = "test.txt"
            editText.setText(content)
        }
    }

    fun inputStream2String(inputStream: InputStream): String {
        var result = ""

        val length = inputStream.available()
        val buffer = ByteArray(length)
        inputStream.read(buffer)

        result = String(buffer)

        return result
    }
}
