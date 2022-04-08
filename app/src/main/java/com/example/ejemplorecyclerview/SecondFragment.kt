package com.example.ejemplorecyclerview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplorecyclerview.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private lateinit var miRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FALLO EDITAR","SEGUNDO FRAGMENTO")
        miRecyclerView=binding.rvContenedores
        miRecyclerView.layoutManager=LinearLayoutManager(activity)
        (activity as MainActivity).miViewModel.listaPeliculas.observe(activity as MainActivity) {
            it?.let{
                miRecyclerView.adapter=Adaptador(this,it)
            }
        }

        binding.fab.setOnClickListener { view ->
            findNavController().navigate(R.id.action_SecondFragment_to_detalleFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}