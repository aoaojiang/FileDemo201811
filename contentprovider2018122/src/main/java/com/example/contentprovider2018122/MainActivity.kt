package com.example.contentprovider2018122

import android.app.Activity
import android.content.ContentResolver
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : Activity() {

    internal lateinit var resolver: ContentResolver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resolver = contentResolver

        button.setOnClickListener() {
            val cursor = resolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null
            )
            val contactsArray = arrayOfNulls<String>(cursor.count)
            val contactsIDs = arrayOfNulls<String>(cursor.count)


            var i = 0
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val ID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))

                contactsArray[i] = "ID: $ID; 姓名：$name"
                contactsIDs[i] = ID
                i++
                cursor.moveToNext()
            }

            cursor.close()

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contactsArray)
            listView.adapter = adapter

            listView.setOnItemClickListener(object : AdapterView.OnItemClickListener {
                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Toast.makeText(this@MainActivity, getPhone(contactsIDs[p2]), Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    fun getPhone(id: String?): String {
        var sb = StringBuffer()
        val phone = resolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
            arrayOf(id), null
        )

        sb.append("Phone: ")
        while (phone.moveToNext()) {
            val phoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            sb.append(phoneNumber + "\n")
        }

        phone.close()
        return sb.toString()
    }
}
