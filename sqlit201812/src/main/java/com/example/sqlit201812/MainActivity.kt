package com.example.sqlit201812

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : Activity() {

    private var db: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = openOrCreateDatabase("people.db", Context.MODE_PRIVATE, null)
        val sql =
            "create table if not exists student" +
                    "(_id integer primary key autoincrement, " +
                    "name text not null, age integer);"
        db!!.execSQL(sql)
    }

    fun onInsert(view: View) {
        val values = ContentValues()

        values.put("name", editText.text.toString())
        values.put("age", Integer.parseInt(editText2.text.toString()))

        db!!.insert("student", null, values)

        editText.setText("")
        editText2.setText("")
    }

    fun onAll(view: View) {
        val cursor = db!!.query(
            "student",
            arrayOf("_id", "name", "age"),
            null, null, null, null, null
        )

        if (cursor.count == 0 || cursor == null)
            return

        cursor.moveToFirst()

        var s = ""

        for (i in 0 until cursor.count) {
            s += "ID: " + cursor.getInt(0) + ", "
            s += "姓名：" + cursor.getString(1) + ", "
            s += "年龄：" + cursor.getInt(2) + "\n"

            cursor.moveToNext()
        }

        textView3.text = s
    }

    fun onQuery(view: View) {
        val id = Integer.parseInt(editText3.text.toString())
        val cursor = db!!.query(
            "student",
            arrayOf("_id", "name", "age"),
            "_id=$id",
            null, null, null, null, null
        )

        if (cursor.count <= 0)
            textView3.text = "没找到！"
        else {
            cursor.moveToFirst()
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val age = cursor.getInt(2)
            val student = Student(id, name, age)

            textView3.text = student.toString()
            editText.setText(student.name)
            editText2.setText(student.age.toString())
        }
    }

    fun onUpdate(view: View) {
        val values = ContentValues()
        values.put("name", editText.text.toString())
        values.put("age", Integer.parseInt(editText2.text.toString()))
        val id = Integer.parseInt(editText3.text.toString())

        val result = db!!.update("student", values, "_id=$id", null)

        if (result <= 0)
            textView3.text = "更新失败！"
        else
            textView3.text = "更新成功！"
    }

    fun onDelete(view: View) {
        val id = Integer.parseInt(editText3.text.toString())
        val result = db!!.delete("student", "_id=$id", null)

        if (result <= 0)
            textView3.text = "删除失败！"
        else
            textView3.text = "删除成功！"
    }
}
