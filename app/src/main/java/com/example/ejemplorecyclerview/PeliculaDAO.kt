package com.example.ejemplorecyclerview

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PeliculaDAO {
    @Query("SELECT * FROM tabla_peliculas ORDER BY año ASC")
    fun mostrarTodas(): Flow<List<Pelicula>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarPelicula(miPelicula:Pelicula)

    @Delete
    suspend fun borrarPelicula(miPelicula: Pelicula)

    @Update
    suspend fun editarPelicula(miPelicula: Pelicula)

    @Query("SELECT * FROM tabla_peliculas where id like :id")
    fun buscarPorId(id:Int):Flow<Pelicula>
}