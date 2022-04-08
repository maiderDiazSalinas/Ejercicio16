package com.example.ejemplorecyclerview

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities= arrayOf(Pelicula::class),version=1, exportSchema = false)
abstract class BaseDatos:RoomDatabase() {

    abstract fun miDao():PeliculaDAO

    companion object{
        @Volatile
        private var INSTANCE:BaseDatos?=null

        fun getDataBase(context:Context):BaseDatos{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDatos::class.java,
                    "peliculas_database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}