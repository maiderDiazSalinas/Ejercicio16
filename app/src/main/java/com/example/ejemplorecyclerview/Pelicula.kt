package com.example.ejemplorecyclerview

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="tabla_peliculas")
data class Pelicula(
    @PrimaryKey(autoGenerate = true) var id:Int=0,
    @NonNull @ColumnInfo(name="titulo") var titulo:String,
    @NonNull @ColumnInfo(name="genero") var genero:String,
    @NonNull @ColumnInfo(name="año") var anio:Int,
    @NonNull @ColumnInfo(name="caratula") var caratula:Int){
}