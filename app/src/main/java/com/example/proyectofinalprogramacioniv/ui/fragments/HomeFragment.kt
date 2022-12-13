package com.example.proyectofinalprogramacioniv.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.example.proyectofinalprogramacioniv.R
import com.example.proyectofinalprogramacioniv.core.ResultTestStatics
import com.example.proyectofinalprogramacioniv.ui.activities.*

class HomeFragment : Fragment(R.layout.fragment_home) {


    private lateinit var builder: AlertDialog.Builder
    private lateinit var btnT: LinearLayout
    private lateinit var btnP: LinearLayout
    private lateinit var btnPerfil: LinearLayout
    private lateinit var btnHis: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        builder = context?.let { AlertDialog.Builder(it) }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }



    fun TestPsychological() {

        builder.setTitle("Alerta!")
            .setMessage("Quieres entrar a Test Psicologico?, es contra reloj")
            .setCancelable(true)
            .setPositiveButton("Si") { dialogInterface, it ->
                //finish()
                val ventanaSiguiente: Intent = Intent(context, PsychologicalActivity::class.java)
                startActivity(ventanaSiguiente)
            }
            .setNegativeButton("No") { dialogInterface, it ->
                dialogInterface.cancel()

            }.show()
    }

    fun TestTheoretical() {

        builder.setTitle("Alerta!")
            .setMessage("Quieres entrar a Test Teorico?, es contra reloj")
            .setCancelable(true)

            .setPositiveButton("Si") { dialogInterface, it ->
               // finish()
                val ventanaSiguiente: Intent = Intent(context, TheoreticalActivity::class.java)
                startActivity(ventanaSiguiente)
            }
            .setNegativeButton("No") { dialogInterface, it ->
                dialogInterface.cancel()

            }.show()
    }

    fun MyPerfil() {

        val ventanaSiguiente: Intent = Intent(context, PerfilActivity::class.java)
        startActivity(ventanaSiguiente)
    }

    fun btnConfiguracion() {

        val ventanaSiguiente: Intent = Intent(context, ConfigActivity::class.java)
        startActivity(ventanaSiguiente)
    }

    fun btnHistory() {

        var correctAw: String = ResultTestStatics.CORRECT_ANSWERST
        var sizeQuest: String = ResultTestStatics.TOTAL_QUESTIONT

        var correctAnP: String = ResultTestStatics.CORRECT_ANSWERSP
        var sizeQuesP: String = ResultTestStatics.TOTAL_QUESTIONP

        if (correctAw.isNotEmpty() && sizeQuest.isNotEmpty() && correctAnP.isNotEmpty() && sizeQuesP.isNotEmpty()) {

            val ventanaSiguiente: Intent = Intent(context, HistoryActivity::class.java)
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
}