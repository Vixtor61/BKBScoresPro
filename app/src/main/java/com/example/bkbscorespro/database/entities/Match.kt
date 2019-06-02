package com.example.bkbscorespro.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "bkb_match",foreignKeys =
    [ForeignKey (entity = Team::class, parentColumns = ["id"],childColumns = ["teamA"]),
    ForeignKey(entity = Team::class,parentColumns = ["id"],childColumns = ["teamB"] )])
data class Match(
    val teamA:Int,
    val teamB:Int,
    @ColumnInfo(name = "score_team_a")
    val scoreTeamA: Int,
    @ColumnInfo(name = "score_team_b")
    val scoreTeamB: Int
/*    ,
    @ColumnInfo(name = "date")
    val date: Date*/

){
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}