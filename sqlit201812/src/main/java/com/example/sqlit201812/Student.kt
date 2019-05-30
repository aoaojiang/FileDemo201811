package com.example.sqlit201812

class Student(internal var id: Int, internal var name: String, internal var age: Int) {

    override fun toString(): String {
        return "Student(id=$id, name='$name', age=$age)"
    }


}