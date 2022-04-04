package com.example.ejemplorecyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.ejemplorecyclerview.databinding.FragmentDetalleBinding
import com.example.ejemplorecyclerview.databinding.FragmentFirstBinding
import com.example.ejemplorecyclerview.databinding.FragmentSecondBinding

class DetalleFragment : Fragment() {

    private var _binding: FragmentDetalleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetalleBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id:Int=arguments?.getInt("id") ?:-1

        if(id!=-1){
            binding.etTitulo.setText((activity as MainActivity).miViewModel.listaPeliculas[id].titulo)
            binding.etGenero.setText((activity as MainActivity).miViewModel.listaPeliculas[id].genero)
            binding.etAnio.setText((activity as MainActivity).miViewModel.listaPeliculas[id].anio.toString())
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
            if(binding.etTitulo.text.isNotEmpty() &&
                binding.etGenero.text.isNotEmpty() &&
                binding.etAnio.text.isNotEmpty()) {
                if (binding.etTitulo.text.toString() != (activity as MainActivity).miViewModel.listaPeliculas[id].titulo ||
                    binding.etGenero.text.toString() != (activity as MainActivity).miViewModel.listaPeliculas[id].genero ||
                    binding.etAnio.text.toString().toInt() != (activity as MainActivity).miViewModel.listaPeliculas[id].anio) {
                    (activity as MainActivity).miViewModel.editarPelicula(
                        id, Pelicula(
                            binding.etTitulo.text.toString(),
                            binding.etGenero.text.toString(),
                            binding.etAnio.text.toString().toInt(),
                            R.drawable.bootstrap_alineacion_ej_1
                        )
                    )
                    Toast.makeText(activity,"Pelicula editada",Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_detalleFragment_to_SecondFragment)
                }
            }
        }
        binding.bBorrar.setOnClickListener {
            (activity as MainActivity).miViewModel.borrarPelicula(id)
            findNavController().navigate(R.id.action_detalleFragment_to_SecondFragment)
        }
        binding.bInsertar.setOnClickListener {
            if(binding.etTitulo.text.isNotEmpty() &&
                binding.etGenero.text.isNotEmpty() &&
                binding.etAnio.text.isNotEmpty()) {
                (activity as MainActivity).miViewModel.insertarPelicula(
                    Pelicula(
                        binding.etTitulo.text.toString(),
                        binding.etGenero.text.toString(),
                        binding.etAnio.text.toString().toInt(),
                        R.drawable.bootstrap_alineacion_ej_1
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