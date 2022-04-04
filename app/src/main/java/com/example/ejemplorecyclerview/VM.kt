package com.example.ejemplorecyclerview

import androidx.lifecycle.ViewModel

class VM: ViewModel() {
    var listaPeliculas: MutableList<Pelicula> = mutableListOf()
    init{
        listaPeliculas=cargarPeliculas()
    }

    private fun cargarPeliculas():MutableList<Pelicula>{
        val lista:MutableList<Pelicula> = mutableListOf()
        lista.add(Pelicula("malefica","aventura",2004,R.drawable.bootstrap_alineacion_ej_1))
        return lista
    }

    fun insertarPelicula(miPelicula:Pelicula){
        listaPeliculas.add(miPelicula)
    }

    fun borrarPelicula(posicion:Int){
        listaPeliculas.removeAt(posicion)
    }

    fun editarPelicula(posicion:Int, miPelicula:Pelicula){
        listaPeliculas[posicion].titulo=miPelicula.titulo
        listaPeliculas[posicion].genero=miPelicula.genero
        listaPeliculas[posicion].anio=miPelicula.anio
    }
}