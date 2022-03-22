package com.example.ejemplorecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

class Adaptador(var fragmento:Fragment):RecyclerView.Adapter<Adaptador.ViewHolder>() {

    inner class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        var tvNumero:TextView
        init{
            tvNumero=v.findViewById(R.id.tvNumero)
            v.setOnClickListener {
                fragmento.findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.layout_contenedor, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNumero.text=String.format("Este es el contenedor $position")
    }

    override fun getItemCount(): Int {
        return 30
    }
}