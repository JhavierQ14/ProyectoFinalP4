package com.example.proyectofinalprogramacioniv.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofinalprogramacioniv.R
import com.example.proyectofinalprogramacioniv.core.ResultTestStatics
import com.example.proyectofinalprogramacioniv.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        obtenerNotas()
    }

    fun obtenerNotas(){

        var correctAwT: String = ResultTestStatics.CORRECT_ANSWERST


        var correctAwP: String = ResultTestStatics.CORRECT_ANSWERSP


        binding.examenPsicological.setText("Examen Psicologico: $correctAwP")
        binding.examenTeorico.setText("Examen Teorico: $correctAwT")






    }
}