package com.rajpurohit.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(val title:String,val discription :String) {
  @PrimaryKey(autoGenerate = true) var id:Int = 0
}