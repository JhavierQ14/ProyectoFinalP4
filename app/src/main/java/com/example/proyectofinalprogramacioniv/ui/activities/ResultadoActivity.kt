package com.example.proyectofinalprogramacioniv.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofinalprogramacioniv.core.ResultTestStatics
import com.example.proyectofinalprogramacioniv.core.SignUserStatics
import com.example.proyectofinalprogramacioniv.databinding.ActivityResultadoBinding

class ResultadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GetInfoUser()
        TerminaTest()
    }

    private fun GetInfoUser(){

        val bundle: Bundle? = intent.extras
      var b = bundle?.getString("Tipo")

        var nameUser: String? = SignUserStatics.nameUser
        var lastNameUser: String? = SignUserStatics.lastNameUser
        var nameCompleteU: String? = nameUser+ "" +lastNameUser
        var correctAw: String = ResultTestStatics.CORRECT_ANSWERST
        var sizeQuest: String = ResultTestStatics.TOTAL_QUESTIONT

        binding.tvName.setText(nameCompleteU)
        if (b == "Psicologico"){

            var correctAnP: String = ResultTestStatics.CORRECT_ANSWERSP
            var sizeQuesP: String = ResultTestStatics.TOTAL_QUESTIONP

            binding.tvScore.setText("Tu puntuacion es $correctAnP de $sizeQuesP")
        }else{

            binding.tvScore.setText("Tu puntuacion es $correctAw de $sizeQuest")

        }

    }

    private fun TerminaTest(){

        binding.btnFinish.setOnClickListener{

            val inte: Intent = Intent(this, HomeActivity::class.java)
            startActivity(inte)
        }
    }
}