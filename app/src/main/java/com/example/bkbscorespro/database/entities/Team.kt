package com.example.bkbscorespro.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "team")
data class Team(
    @ColumnInfo(name = "team_name")
    val  teamName:String
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}