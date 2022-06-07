package com.example.appmaestro.presentation.ListadoMaterias

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.appmaestro.R
import com.example.appmaestro.core.extension.failure
import com.example.appmaestro.core.extension.observe
import com.example.appmaestro.core.presentation.BaseFragment
import com.example.appmaestro.core.presentation.BaseViewState
import com.example.appmaestro.databinding.ListadoMateriasFragmentBinding
import com.example.appmaestro.domain.model.Maestro
import com.example.appmaestro.domain.model.Materia
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@AndroidEntryPoint
@WithFragmentBindings
class ListadoMateriasFragment : BaseFragment(R.layout.listado_materias_fragment) {

    private lateinit var binding: ListadoMateriasFragmentBinding
    private lateinit var adapter: ListadoMateriasAdapter
    private val listadoMateriasViewModel by viewModels<ListadoMateriasViewModel>()



    private lateinit var listaMaterias: MutableList<Materia>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listaMaterias = mutableListOf()
        listadoMateriasViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure,::handleFailure)
            doGetMaestro()
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when(state){
            is ListadoMateriasViewState.materiaReceived -> saveMateria(state.materia)
            is ListadoMateriasViewState.maestroReceived -> setMaestro(state.maestro)
            is ListadoMateriasViewState.UserNotFound -> navController.navigate(ListadoMateriasFragmentDirections.actionListadoMateriasFragmentToLoginFragment())
        }
    }

    fun setMaestro(maestro: Maestro){
        maestro.materias.forEach{
            listadoMateriasViewModel.doGetMateria(it)
        }
    }

    fun saveMateria(materias : List<Materia>){
        adapter = ListadoMateriasAdapter()
        listaMaterias.add(materias[0])
        adapter.addData(listaMaterias)
        adapter.listener = {
            navController.navigate(ListadoMateriasFragmentDirections.actionListadoMateriasFragmentToDetalleMateriaFragment(it))
        }
        binding.rcvMaterias.apply{
            adapter = this@ListadoMateriasFragment.adapter
        }
    }

    override fun setBinding(view: View) {
        binding = ListadoMateriasFragmentBinding.bind(view)
        binding.lifecycleOwner=this

    }
}