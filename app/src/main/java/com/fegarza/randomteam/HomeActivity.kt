package com.fegarza.randomteam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fegarza.randomteam.adaptadores.EquiposAdapter
import com.fegarza.randomteam.databinding.ActivityHomeBinding
import com.fegarza.randomteam.entidades.Equipo
import java.util.ArrayList

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:  ActivityHomeBinding
    private  var equipos = ArrayList<Equipo>();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.rvPersonas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false  )

        for(x in 1..4){
            this.equipos.add(Equipo("Equipo numero ${x}"))
        }

        var miAdapdador = EquiposAdapter();
        miAdapdador.equipos = this.equipos;
        binding.rvPersonas.adapter = miAdapdador;
    }

    fun agregarEquipo(){

        this.binding.textNombreEquipo.editText?.let {
            if(it.text.toString().isNotEmpty()){
                this.equipos.add(Equipo(it.text.toString()));
            }else{
                //Alerta de error
            }
        } ?: run {
            //Alerta de error
        }


    }



}