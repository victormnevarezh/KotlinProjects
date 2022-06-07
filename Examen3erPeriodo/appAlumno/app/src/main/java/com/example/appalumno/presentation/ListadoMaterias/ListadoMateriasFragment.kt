package com.example.appalumno.presentation.ListadoMaterias

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.appalumno.R
import com.example.appalumno.core.extension.failure
import com.example.appalumno.core.extension.observe
import com.example.appalumno.core.presentation.BaseFragment
import com.example.appalumno.core.presentation.BaseViewState
import com.example.appalumno.databinding.FragmentListadoMateriasBinding
import com.example.appalumno.domain.model.Alumno
import com.example.appalumno.domain.model.AlumnoUpdt
import com.example.appalumno.domain.model.Materia
import com.example.appalumno.presentation.Login.LoginViewState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@AndroidEntryPoint
@WithFragmentBindings
class ListadoMateriasFragment : BaseFragment(R.layout.fragment_listado_materias) {

    private lateinit var binding: FragmentListadoMateriasBinding
    private lateinit var adapter: ListadoMateriasAdapter
    private lateinit var listaMaterias : MutableList<Materia>


    private val listadoMateriasViewModel by viewModels<ListadoMateriasViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listaMaterias = mutableListOf()
        listadoMateriasViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure,::handleFailure)
            doGetAlumnoSP()
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when(state){
            is ListadoMateriasViewState.MateriaReceived -> saveMateria(state.materia)
            is ListadoMateriasViewState.AlumnoReceived -> setAlumno(state.alumno)
            is ListadoMateriasViewState.UserNotFound -> navController.navigate(ListadoMateriasFragmentDirections.actionListadoMateriasFragmentToLoginFragment())
        }
    }

    fun setAlumno(alumno:Alumno){
        alumno.materias.forEach{
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
        binding = FragmentListadoMateriasBinding.bind(view)
        binding.lifecycleOwner=this
    }


}