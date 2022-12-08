package com.example.proyectofinalprogramacioniv.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinalprogramacioniv.core.ResultTestStatics
import com.example.proyectofinalprogramacioniv.ui.activities.ConfigActivity
import com.example.proyectofinalprogramacioniv.ui.activities.EstudiarActivity
import com.example.proyectofinalprogramacioniv.ui.activities.PerfilActivity
import com.example.proyectofinalprogramacioniv.core.SignUserStatics
import com.example.proyectofinalprogramacioniv.data.datasources.FirestoreNetwork
import com.example.proyectofinalprogramacioniv.databinding.ActivityHomeBinding
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {

    //
    private lateinit var binding: ActivityHomeBinding

    private lateinit var builder: AlertDialog.Builder

    private val connectionFirebase = FirestoreNetwork.ProviderFirebaseFirestore()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        builder = AlertDialog.Builder(this)

        SignUserStatics.GetDataUser()

    }

    fun TestPsychological(view: View) {

        builder.setTitle("Alerta!")
            .setMessage("Quieres entrar a Test Psicologico?,es contra reloj")
            .setCancelable(true)
            .setPositiveButton("Si") { dialogInterface, it ->
                finish()
                val ventanaSiguiente: Intent = Intent(this, PsychologicalActivity::class.java)
                startActivity(ventanaSiguiente)
            }
            .setNegativeButton("No") { dialogInterface, it ->
                dialogInterface.cancel()

            }.show()
    }

    fun TestTheoretical(view: View) {

        builder.setTitle("Alerta!")
            .setMessage("Quieres entrar a Test Teorico?,es contra reloj")
            .setCancelable(true)

            .setPositiveButton("Si") { dialogInterface, it ->
                finish()
                val ventanaSiguiente: Intent = Intent(this, TheoreticalActivity::class.java)
                startActivity(ventanaSiguiente)
            }
            .setNegativeButton("No") { dialogInterface, it ->
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

    fun GoStudingTest(view: View) {

        connectionFirebase.collection("Questions").document("Pregunta1").set(

            hashMapOf(
                "cod_state_test" to 1,
                "test_name" to "Teorico",
                "description_question" to "enunciado pregunta",
                "img_question" to "urlimagen",
                "answer_question" to listOf<String>("correcta","respuesta1","respuesta2","respuesta3","respuesta14")
            )
        )
        Toast.makeText(this,"Registro exitoso", Toast.LENGTH_LONG).show()

    }
    fun btnHistory (view: View) {

        var correctAw: String = ResultTestStatics.CORRECT_ANSWERST
        var sizeQuest: String = ResultTestStatics.TOTAL_QUESTIONT

        var correctAnP: String = ResultTestStatics.CORRECT_ANSWERSP
        var sizeQuesP: String = ResultTestStatics.TOTAL_QUESTIONP

        if (correctAw.isNotEmpty()&& sizeQuest.isNotEmpty() && correctAnP.isNotEmpty() && sizeQuesP.isNotEmpty()){

            val ventanaSiguiente: Intent = Intent(this, HistoryActivity::class.java)
            startActivity(ventanaSiguiente)
        }else {
            builder.setTitle("Alerta!")
                .setMessage("Debes realizar primero los test")
                .setCancelable(true)

                .setPositiveButton("Ok") { dialogInterface, it ->


                }
                .show()
        }


    }

    fun btnEstudiar (view: View) {

        val ventanaSiguiente: Intent = Intent(this, EstudiarActivity::class.java)
        startActivity(ventanaSiguiente)
    }



}