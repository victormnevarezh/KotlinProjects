package com.example.appmaestro.presentation.ListadoAlumnoMateria

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
import com.example.appmaestro.databinding.DetalleMateriaFragmentBinding
import com.example.appmaestro.databinding.ListadoAlumnosMateriaFragmentBinding
import com.example.appmaestro.databinding.ListadoMateriasFragmentBinding
import com.example.appmaestro.domain.model.Alumno
import com.example.appmaestro.domain.model.Materia
import com.example.appmaestro.presentation.DetalleMateria.DetalleMateriaFragmentArgs
import com.example.appmaestro.presentation.DetalleMateria.DetalleMateriaViewModel
import com.example.appmaestro.presentation.ListadoMaterias.ListadoMateriasAdapter
import com.example.appmaestro.presentation.ListadoMaterias.ListadoMateriasFragmentDirections
import com.example.appmaestro.presentation.ListadoMaterias.ListadoMateriasViewState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@AndroidEntryPoint
@WithFragmentBindings
class ListadoAlumnosMateriaFragment : BaseFragment(R.layout.listado_alumnos_materia_fragment) {

    private lateinit var binding: ListadoAlumnosMateriaFragmentBinding
    private val args: ListadoAlumnosMateriaFragmentArgs by navArgs()
    private lateinit var listaAlumnos : MutableList<Alumno>
    private lateinit var adapter: ListadoAlumnoMateriaAdapter

    private val listadoAlumnosMateriaViewModel by viewModels<ListadoAlumnosMateriaViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listaAlumnos = mutableListOf()

        listadoAlumnosMateriaViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)

            args.materia.matriculados.forEach {
                doSaveMateriaSP(args.materia)
                doGetAlumnoByMatricula(it)
            }
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is ListadoAlumnoMateriaViewState.alumnoReceived -> saveAlumno(state.alumno)
        }
    }

    fun saveAlumno(alumnos: List<Alumno>) {
        adapter = ListadoAlumnoMateriaAdapter()
        listaAlumnos.add(alumnos[0])
        adapter.addData(listaAlumnos)
        adapter.listener = {
            navController.navigate(
                ListadoAlumnosMateriaFragmentDirections.actionListadoAlumnosMateriaFragmentToDetalleCalificacionAlumnoFragment(
                    it
                )
            )
        }
        binding.rcvAlumnos.apply {
            adapter = this@ListadoAlumnosMateriaFragment.adapter
        }
    }

    override fun setBinding(view: View) {
        binding = ListadoAlumnosMateriaFragmentBinding.bind(view)
        binding.lifecycleOwner = this
    }

}