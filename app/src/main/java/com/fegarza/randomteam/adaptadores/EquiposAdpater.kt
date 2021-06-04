package com.fegarza.randomteam.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fegarza.randomteam.R
import com.fegarza.randomteam.databinding.LayoutEquipoItemBinding
import com.fegarza.randomteam.entidades.Equipo
import java.util.ArrayList

class EquiposAdapter: RecyclerView.Adapter<EquiposAdapter.EquiposViewHolder>(){

    var equipos = ArrayList<Equipo>();



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquiposViewHolder {
        var layout = LayoutInflater.from(parent.context).inflate(R.layout.layout_equipo_item, null);
        return EquiposViewHolder(layout);
    }

    override fun getItemCount(): Int {
        return equipos.size;
    }

    override fun onBindViewHolder(holder: EquiposViewHolder, position: Int) {
        holder.cargarEquipo(this.equipos.get(position))
    }

    class EquiposViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var equipo = Equipo("");
        private lateinit var binding : LayoutEquipoItemBinding

        init{
            binding = LayoutEquipoItemBinding.bind(this.itemView)
        }

        fun cargarEquipo(equipo:  Equipo){
            this.equipo = equipo;
            this.binding.textNombre.text = equipo.nombre
        }

    }


}