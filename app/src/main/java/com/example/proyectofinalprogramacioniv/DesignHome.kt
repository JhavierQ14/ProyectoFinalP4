package com.example.proyectofinalprogramacioniv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.proyectofinalprogramacioniv.R

class DesignHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_design_home)
    }

    fun btnMyPerfil(Vistas: View) {

        val ventanaSiguiente: Intent = Intent(applicationContext,PerfilActivity::class.java)
        startActivity(ventanaSiguiente)
    }


}