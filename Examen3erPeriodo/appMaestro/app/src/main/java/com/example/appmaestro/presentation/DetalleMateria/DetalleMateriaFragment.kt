package com.example.appmaestro.presentation.DetalleMateria

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
import com.example.appmaestro.databinding.DetalleMateriaFragmentBinding
import com.example.appmaestro.presentation.LoginFragment.LoginFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@AndroidEntryPoint
@WithFragmentBindings
class DetalleMateriaFragment : BaseFragment(R.layout.detalle_materia_fragment) {

    private lateinit var binding : DetalleMateriaFragmentBinding
    private val args : DetalleMateriaFragmentArgs by navArgs()

    private val detalleMateriaViewModel by  viewModels<DetalleMateriaViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detalleMateriaViewModel.apply{
            observe(state, ::onViewStateChanged)
            failure(failure,::handleFailure)
        }
    }

    override fun setBinding(view: View) {
        binding = DetalleMateriaFragmentBinding.bind(view)
        binding.lifecycleOwner = this
        binding.item = args.materia
        var listaDias = "Días: "
        var listaHoras = "Horas: "
        args.materia.dias.forEach {
            if(args.materia.dias.indexOf(it)!=args.materia.dias.size-1){
                listaDias += "$it, "
            }else{
                listaDias += "$it "
            }
        }
        binding.txvDias.setText(listaDias)
        args.materia.horas.forEach {
            if(args.materia.horas.indexOf(it)!=args.materia.horas.size-1){
                listaHoras += "$it, "
            }else{
                listaHoras += "$it "
            }
        }
        binding.txvHoras.setText(listaHoras)

        binding.btnListadoAlumnos.setOnClickListener {
            navController.navigate(DetalleMateriaFragmentDirections.actionDetalleMateriaFragmentToListadoAlumnosMateriaFragment(args.materia))
        }
    }

}