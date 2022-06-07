package com.example.appmaestro.presentation.ListadoAlumnoMateria

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmaestro.databinding.RowAlumnoBinding
import com.example.appmaestro.databinding.RowMateriaBinding
import com.example.appmaestro.domain.model.Alumno
import com.example.appmaestro.domain.model.Materia
import com.example.appmaestro.presentation.ListadoMaterias.ListadoMateriasAdapter

class ListadoAlumnoMateriaAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list : MutableList<Alumno> = mutableListOf()

    lateinit var listener : (alumno: Alumno) -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list:List<Alumno>){
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = ViewHolderItem(
        RowAlumnoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = (holder as ViewHolderItem).bind(
        list[position], listener
    )

    override fun getItemCount() = list.size

    class ViewHolderItem(private val binding: RowAlumnoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (data : Alumno, listener: (alumno: Alumno)->Unit){
            binding.item = data

            binding.root.setOnClickListener{
                listener(data)
            }
        }
    }
}