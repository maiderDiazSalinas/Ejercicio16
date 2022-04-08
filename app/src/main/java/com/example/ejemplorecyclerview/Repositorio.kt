package com.example.ejemplorecyclerview

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class Repositorio(val miDao:PeliculaDAO) {
    val listaPeliculas: Flow<List<Pelicula>> =miDao.mostrarTodas()

    @Suppress
    @WorkerThread
    suspend fun insertarPelicula(miPelicula: Pelicula){
        miDao.insertarPelicula(miPelicula)
    }

    @Suppress
    @WorkerThread
    suspend fun borrarPelicula(miPelicula: Pelicula){
        miDao.borrarPelicula(miPelicula)
    }

    @Suppress
    @WorkerThread
    suspend fun editarPelicula(miPelicula: Pelicula){
        miDao.editarPelicula(miPelicula)
    }

    fun buscarPorId(miId:Int):Flow<Pelicula>{
        return miDao.buscarPorId(miId)
    }

}