package com.example.ejemplorecyclerview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.ejemplorecyclerview.databinding.FragmentDetalleBinding

class DetalleFragment : Fragment() {

    private var _binding: FragmentDetalleBinding? = null
    private val binding get() = _binding!!

    private lateinit var miPelicula:Pelicula

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetalleBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("FALLO EDITAR","Fragmento detalle")

        val id:Int=arguments?.getInt("id") ?:-1

        if(id!=-1){
            (activity as MainActivity).miViewModel.buscarPorId(id)
            (activity as MainActivity).miViewModel.miPelicula.observe(activity as MainActivity, Observer {
                it?.let {
                    miPelicula = it
                    binding.etTitulo.setText(miPelicula.titulo)
                    binding.etGenero.setText(miPelicula.genero)
                    binding.etAnio.setText(miPelicula.anio.toString())
                }
            })
            binding.bEditar.isVisible=true
            binding.bInsertar.isVisible=false
            binding.bBorrar.isVisible=true
        }
        else{
            binding.bEditar.isVisible=false
            binding.bInsertar.isVisible=true
            binding.bBorrar.isVisible=false
        }

        binding.bEditar.setOnClickListener {
                if (binding.etTitulo.text.isNotEmpty() &&
                    binding.etGenero.text.isNotEmpty() &&
                    binding.etAnio.text.isNotEmpty()
                ) {
                    if (binding.etTitulo.text.toString() != miPelicula.titulo ||
                        binding.etGenero.text.toString() != miPelicula.genero ||
                        binding.etAnio.text.toString().toInt() != miPelicula.anio
                    ) {
                       /*miPelicula.titulo=binding.etTitulo.text.toString()
                       miPelicula.genero=binding.etGenero.text.toString()
                       miPelicula.anio= binding.etAnio.text.toString().toInt()*/
                        (activity as MainActivity).miViewModel.editarPelicula(miPelicula)
                        Toast.makeText(activity, "Pelicula editada", Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_detalleFragment_to_SecondFragment)
                    }
                    else Toast.makeText(activity,"No has cambiado ningún dato", Toast.LENGTH_LONG).show()
                }
            else Toast.makeText(activity, "Algún dato esta vacio", Toast.LENGTH_LONG).show()
        }

        binding.bBorrar.setOnClickListener {
            (activity as MainActivity).miViewModel.borrarPelicula(miPelicula)
            findNavController().navigate(R.id.action_detalleFragment_to_SecondFragment)
        }

        binding.bInsertar.setOnClickListener {
            if(binding.etTitulo.text.isNotEmpty() &&
                binding.etGenero.text.isNotEmpty() &&
                binding.etAnio.text.isNotEmpty()) {
                (activity as MainActivity).miViewModel.insertarPelicula(
                    Pelicula(
                        titulo=binding.etTitulo.text.toString(),
                        genero=binding.etGenero.text.toString(),
                        anio=binding.etAnio.text.toString().toInt(),
                        caratula=R.drawable.bootstrap_alineacion_ej_1
                    )
                )
                Toast.makeText(activity,"Pelicula insertada",Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_detalleFragment_to_SecondFragment)
            }
            else{
                Toast.makeText(activity,"No has insertado todos los datos",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}