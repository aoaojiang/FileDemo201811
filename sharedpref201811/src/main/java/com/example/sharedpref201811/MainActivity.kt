package com.example.sharedpref201811

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sp = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val username = sp.getString("username", "无名氏")

        editText.setText(username)
    }

    override fun onDestroy() {
        super.onDestroy()

        val sp = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val editor = sp.edit()

        val username = editText.text.toString()
        editor.putString("username", username)
        editor.commit()
    }
}
