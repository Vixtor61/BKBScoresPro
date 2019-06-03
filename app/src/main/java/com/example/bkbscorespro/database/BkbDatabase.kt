package com.example.bkbscorespro.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bkbscorespro.database.daos.MatchDao
import com.example.bkbscorespro.database.daos.TeamDao
import com.example.bkbscorespro.database.entities.Match
import com.example.bkbscorespro.database.entities.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Match::class,Team::class],version = 1,exportSchema = false)//en caso de mas entidades se agregan al arreglo.. despues de la version si se cambi se tiene que migrar
public abstract class BkbDatabase: RoomDatabase(){

    abstract fun teamDao():TeamDao
    abstract fun matchDao():MatchDao

    //aqui se agregan todos los DAOs

    companion object {//solo se necesita una entidad de esta clase SInglenton

        @Volatile//notifica cambios a todos los hilos que lo estan usando
        private var INSTANCE: BkbDatabase?=null

        fun getInstance(context: Context,
                        scope: CoroutineScope
        ): BkbDatabase {
            val temInstance = INSTANCE
            if(temInstance!=null){
                return temInstance
            }

            synchronized(this){//para que solo un hilo la use y puedan crearse dos bases
                val instance = Room
                    .databaseBuilder(context, BkbDatabase::class.java,"BKB_Database")
                    .fallbackToDestructiveMigration()
                    .addCallback(BookDatabaseCallback(scope))
                    .build()
                INSTANCE =instance
                return instance
            }
        }

        private class BookDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.teamDao())
                        populateDatabase(database.matchDao())


                    }
                }
            }
        }

        suspend fun populateDatabase(teamDao: TeamDao){

        }
        suspend fun populateDatabase(matchDao: MatchDao){

        }


    }
}



