package com.example.appalumno.presentation.ListadoMaterias

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appalumno.databinding.RowMateriaBinding
import com.example.appalumno.domain.model.Materia

class ListadoMateriasAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list : MutableList<Materia> = mutableListOf()

    lateinit var listener : (materia:Materia) -> Unit

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list:List<Materia>){
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = ViewHolderItem(
        RowMateriaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = (holder as ViewHolderItem).bind(
        list[position], listener
    )

    override fun getItemCount() = list.size

    class ViewHolderItem(private val binding: RowMateriaBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (data:Materia, listener: (materia: Materia)->Unit){
            binding.item = data
            var lista = ""
            data.dias.forEach {
                if(data.dias.indexOf(it)!=data.dias.size-1){
                    lista += "$it, "
                }else{
                    lista += "$it "
                }

            }
            binding.txvHora.setText(lista)
            binding.root.setOnClickListener { listener(data)}
        }
    }

}