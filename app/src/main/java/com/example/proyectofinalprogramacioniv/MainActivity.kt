package com.example.proyectofinalprogramacioniv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun btnSigResgiter(Vistas: View){
        val ventanaSiguiente: Intent = Intent(applicationContext,RegistrarseActivity::class.java)
        startActivity(ventanaSiguiente)
    }

    fun loginbtn(Vistas: View){

        val ventanaSiguiente: Intent = Intent(applicationContext,DesignHome::class.java)
        startActivity(ventanaSiguiente)
    }

    fun IniciarSesion(){



    }
}