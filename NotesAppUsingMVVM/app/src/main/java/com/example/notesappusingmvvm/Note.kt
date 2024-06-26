package com.example.notesappusingmvvm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
class Note(@ColumnInfo(name="text") val text:String) {

    @PrimaryKey(autoGenerate = true) var id:Int = 0
}