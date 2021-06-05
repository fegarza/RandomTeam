package com.fegarza.randomteam

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fegarza.randomteam.adaptadores.EquiposAdapter
import com.fegarza.randomteam.databinding.ActivityHomeBinding
import com.fegarza.randomteam.entidades.Equipo
import com.google.android.material.snackbar.Snackbar
import java.util.*


class HomeActivity : AppCompatActivity() {

    private lateinit var binding:  ActivityHomeBinding
    private  var equipos = ArrayList<Equipo>();


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)


        this.cargarRecyclerView(this.binding.switchOcultar.isChecked)

        binding.toolbar.inflateMenu(R.menu.menu_home)
        setSupportActionBar(  binding.toolbar)

        //binding.switchOcultar.setOnCheckedChangeListener { buttonView, isChecked -> binding.switchOcultar.isChecked }
    }

    fun agregarEquipo(view: View){
        this.binding.textNombreEquipo.editText?.let {
            if(it.text.toString().isNotEmpty()){
                this.equipos.add(Equipo(it.text.toString()));
                this.cargarRecyclerView(this.binding.switchOcultar.isChecked)
                Toast.makeText(getApplicationContext(), "Se ha agregado con exito el equipo ${it.text.toString()}",Toast.LENGTH_SHORT).show();
                it.text.clear()
            }else{
                Toast.makeText(getApplicationContext(), "Error, introduce un nombre de equipo valido",Toast.LENGTH_SHORT).show();
            }
        } ?: run {
            Toast.makeText(getApplicationContext(), "Error, introduce un nombre de equipo valido",Toast.LENGTH_SHORT).show();
        }
    }


    fun cargarRecyclerView(ocultarNombres: Boolean){

        binding.rvPersonas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false  )

        var miAdapdador = EquiposAdapter();
        miAdapdador.equipos = this.equipos;
        miAdapdador.ocultarNombres = ocultarNombres;
        miAdapdador.clickListenner = View.OnClickListener {
            //Toast.makeText(getApplicationContext(), "hi",Toast.LENGTH_SHORT).show();
        }
        binding.rvPersonas.adapter = miAdapdador;

    }

    fun generarOrden(view: View){
        Collections.shuffle(this.equipos);
        this.cargarRecyclerView(this.binding.switchOcultar.isChecked)
        val snackbar = Snackbar.make(
            findViewById(android.R.id.content),
            "Se ha generado el orden con exito",
            Snackbar.LENGTH_LONG
        )
        snackbar.show()
    }

    fun cambiarOrden(view: View){
        Collections.reverse(this.equipos);
        this.cargarRecyclerView(this.binding.switchOcultar.isChecked)
        Toast.makeText(this, "Se ha cancelado la operacion.", Toast.LENGTH_LONG).show()
    }

    fun ocultarNombres(view: View){
        this.cargarRecyclerView(this.binding.switchOcultar.isChecked)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menu_salir -> {
                val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
                alertDialog.setTitle("Salir")
                alertDialog.setMessage("¿Desea realmente salir de la aplicacion?")
                alertDialog.setPositiveButton(
                    "yes"
                ) { _, _ ->
                    val homeIntent = Intent(Intent.ACTION_MAIN)
                    homeIntent.addCategory(Intent.CATEGORY_HOME)
                    homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(homeIntent)
                }
                alertDialog.setNegativeButton(
                    "No"
                ) { _, _ ->
                    Toast.makeText(this, "Se ha cancelado la operacion.", Toast.LENGTH_LONG).show()
                }
                val alert: AlertDialog = alertDialog.create()
                alert.setCanceledOnTouchOutside(false)
                alert.show()
                return true
            }
            R.id.menu_reiniciar -> {
                val snackbar = Snackbar.make(
                    findViewById(android.R.id.content),
                    "Se ha reiniciado el generador con exito",
                    Snackbar.LENGTH_LONG
                )
                snackbar.show()
                this.equipos.clear();
                this.binding.textNombreEquipo.editText?.text?.clear()
                this.cargarRecyclerView(this.binding.switchOcultar.isChecked);
                return true;
            }

            else -> super.onOptionsItemSelected(item)
        }
    }




}