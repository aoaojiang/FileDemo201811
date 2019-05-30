package com.example.contentprovider201812

import android.app.Activity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val provider = contentResolver
        val cursor = provider.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            arrayOf("_id", "title", "artist"), null, null, null
        )

        val adapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_1,
            cursor,
            arrayOf(MediaStore.Audio.AudioColumns.TITLE, MediaStore.Audio.AudioColumns.ARTIST),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        list1.adapter = adapter
    }
}
