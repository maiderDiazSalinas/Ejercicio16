package com.example.ejemplorecyclerview

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class VM(private val miRepositorio:Repositorio): ViewModel() {

    var listaPeliculas: LiveData<List<Pelicula>> = miRepositorio.listaPeliculas.asLiveData()
    lateinit var miPelicula: LiveData<Pelicula>
    /*init{
        listaPeliculas=cargarPeliculas()
    }

    private fun cargarPeliculas():MutableList<Pelicula>{
        val lista:MutableList<Pelicula> = mutableListOf()
        lista.add(Pelicula(0,"malefica","aventura",2004,R.drawable.bootstrap_alineacion_ej_1))
        return lista
    }*/

    fun insertarPelicula(miPelicula:Pelicula) = viewModelScope.launch{
        miRepositorio.insertarPelicula(miPelicula)
        listaPeliculas= miRepositorio.listaPeliculas.asLiveData()
    }

    fun borrarPelicula(miPelicula: Pelicula) = viewModelScope.launch{
        miRepositorio.borrarPelicula(miPelicula)
        listaPeliculas= miRepositorio.listaPeliculas.asLiveData()
    }

    fun editarPelicula(miPelicula:Pelicula) = viewModelScope.launch{
        miRepositorio.editarPelicula(miPelicula)
        listaPeliculas= miRepositorio.listaPeliculas.asLiveData()
    }

    fun buscarPorId(miId:Int) = viewModelScope.launch {
        miPelicula=miRepositorio.buscarPorId(miId).asLiveData()
    }
}

class PeliculasViewModelFactory(private val miRepositorio: Repositorio):ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass:Class<T>):T {
        if (modelClass.isAssignableFrom(VM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VM(miRepositorio) as T
        }
        throw IllegalArgumentException("clase ViewModel desconocida")
    }
}