package com.example.proyectofinalprogramacioniv.UI.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.example.proyectofinalprogramacioniv.*
import com.example.proyectofinalprogramacioniv.core.SignUserStatics
import kotlin.random.Random

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

        SignUserStatics.GetDataUser()

    }

    override fun onStart() {
        super.onStart()

        /*position = (0..6).random()
        var miarray = arrayListOf<String>("Carlos","Karla","Khaterine","Javier","Giovani","Nelson","Alvaro")
        var mostar = miarray[position]
        Log.d("Random", "$mostar")*/
        var randomValues = Random.nextInt(0, 100)
        var randomValues1 = Random.nextInt(0, 100)
        var miarray = arrayListOf<String>("Carlos","Karla","Khaterine","Javier","Giovani","Nelson","Alvaro")
        //var mostar = miarray[]
        Log.d("Random", "$randomValues")
        Log.d("Random1", "$randomValues1")
    }

    fun TestPsychological(view: View){

        builder.setTitle("Alerta!")
            .setMessage("Quieres entrar a Test Psicologico?,es contra reloj")
            .setCancelable(true)

            .setPositiveButton("Si"){dialogInterface,it ->
                finish()
                val ventanaSiguiente: Intent = Intent(this, PsychologicalActivity::class.java)
                startActivity(ventanaSiguiente)
            }
            .setNegativeButton("No"){dialogInterface,it ->
                dialogInterface.cancel()

            }

            .show()

    }

    fun TestTheoretical(view: View) {

        builder.setTitle("Alerta!")
            .setMessage("Quieres entrar a Test Teorico?,es contra reloj")
            .setCancelable(true)

            .setPositiveButton("Si"){dialogInterface,it ->
                finish()
                val ventanaSiguiente: Intent = Intent(this, TheoreticalActivity::class.java)
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

    fun StudingTest(view: View){

        var randomValues = Random.nextInt(0, 100)
        var randomValues1 = Random.nextInt(0, 100)
        var miarray = arrayListOf<String>("Carlos","Karla","Khaterine","Javier","Giovani","Nelson","Alvaro")
        //var mostar = miarray[]
        Log.d("Random", "$randomValues")
        Log.d("Random1", "$randomValues1")

    }

}