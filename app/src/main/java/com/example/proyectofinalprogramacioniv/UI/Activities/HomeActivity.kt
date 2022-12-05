package com.example.proyectofinalprogramacioniv.UI.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.proyectofinalprogramacioniv.*

class HomeActivity : AppCompatActivity() {

    private lateinit var myperfilBtn: LinearLayout
    private lateinit var questionbtn: LinearLayout
    private lateinit var  btnConfiguracion: LinearLayout
    private  lateinit var  btnQuestionPs: LinearLayout
    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnConfiguracion = findViewById(R.id.btnConfig)
        myperfilBtn = findViewById(R.id.btnMyPerfil)
        questionbtn = findViewById(R.id.btnQuestionT)
        btnQuestionPs = findViewById(R.id.btnQuestionP)


        builder = AlertDialog.Builder(this)


    }

    fun AlertaDialog(view: View){

        builder.setTitle("Alerta!")
            .setMessage("Quieres entrar a Test Psicologico?,es contra reloj")
            .setCancelable(true)

            .setPositiveButton("Si"){dialogInterface,it ->
                finish()
                val ventanaSiguiente: Intent = Intent(this, TimeQuestions::class.java)
                startActivity(ventanaSiguiente)
            }
            .setNegativeButton("No"){dialogInterface,it ->
                dialogInterface.cancel()

            }

            .show()




    }
    fun questionbtn(view: View) {

        builder.setTitle("Alerta!")
            .setMessage("Quieres entrar a Test Teorico?,es contra reloj")
            .setCancelable(true)

            .setPositiveButton("Si"){dialogInterface,it ->
                finish()
                val ventanaSiguiente: Intent = Intent(this, QuestionActivity::class.java)
                startActivity(ventanaSiguiente)
            }
            .setNegativeButton("No"){dialogInterface,it ->
                dialogInterface.cancel()

            }

            .show()
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