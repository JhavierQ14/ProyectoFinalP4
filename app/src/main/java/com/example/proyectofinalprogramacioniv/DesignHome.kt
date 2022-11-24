package com.example.proyectofinalprogramacioniv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.example.proyectofinalprogramacioniv.R

class DesignHome : AppCompatActivity() {

    private lateinit var myperfilBtn: LinearLayout
    private lateinit var questionbtn: LinearLayout
    private lateinit var  btnConfiguracion: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_design_home)

        btnConfiguracion = findViewById(R.id.btnConfig)
        myperfilBtn = findViewById(R.id.btnMyPerfil)
        questionbtn = findViewById(R.id.btnQuestionP)

    }
    fun questionbtn(view: View) {

        val ventanaSiguiente: Intent = Intent(this, QuestionActivity::class.java)
        startActivity(ventanaSiguiente)
    }

    fun MyPerfil(view: View) {

        val ventanaSiguiente: Intent = Intent(this, PerfilActivity::class.java)
        startActivity(ventanaSiguiente)
    }

    fun btnConfiguracion(view: View) {

        val ventanaSiguiente: Intent = Intent(this, ConfigActivity::class.java)
        startActivity(ventanaSiguiente)
    }


}