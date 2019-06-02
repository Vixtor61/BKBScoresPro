package com.example.bkbscorespro.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class Team(
    @ColumnInfo(name = "team_name")
    val  teamName:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}