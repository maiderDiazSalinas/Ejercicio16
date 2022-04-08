package com.example.ejemplorecyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

class Adaptador(var fragmento:Fragment, var lista:List<Pelicula>):RecyclerView.Adapter<Adaptador.ViewHolder>() {

    inner class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        var caratula:ImageView=v.findViewById(R.id.ivCaratula)
        var titulo:TextView=v.findViewById(R.id.tvTitulo)
        var genero:TextView=v.findViewById(R.id.tvGenero)
        var anio:TextView=v.findViewById(R.id.tvAnio)
        var posicion:Int=-1
        init{
            v.setOnClickListener {
                val miBundle:Bundle= bundleOf("id" to this.posicion)
                fragmento.findNavController().navigate(R.id.action_SecondFragment_to_detalleFragment, miBundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.layout_contenedor, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val drawable: Drawable? = ResourcesCompat.getDrawable(resources, R.drawable.myimage, null)
        holder.titulo.text=lista[position].titulo
        holder.genero.text=lista[position].genero
        holder.anio.text=lista[position].anio.toString()
        holder.caratula.setImageResource(lista[position].caratula)
        holder.posicion=lista[position].id
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}