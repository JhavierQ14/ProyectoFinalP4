package com.example.proyectofinalprogramacioniv.ui.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.core.content.ContextCompat
import com.example.proyectofinalprogramacioniv.R
import com.example.proyectofinalprogramacioniv.core.ResultTestStatics
import com.example.proyectofinalprogramacioniv.databinding.ActivityTheoreticalBinding

class TheoreticalActivity : AppCompatActivity(), OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<ModelQuestionTeorico>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0

    private lateinit var biding: ActivityTheoreticalBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        biding = ActivityTheoreticalBinding.inflate(layoutInflater)
        setContentView(biding.root)

        mQuestionList = Constants.getQuestionTeorico()
        SetQuestion()

        biding.tvPrimeraOpcion.setOnClickListener(this)
        biding.tvSegundaOpcion.setOnClickListener(this)
        biding.tvTerceraOpcion.setOnClickListener(this)
        biding.tvCuartaOpcion.setOnClickListener(this)
        biding.btnSiguiente.setOnClickListener(this)


    }
    private fun SetQuestion(){

        val questionTeorico = mQuestionList!![mCurrentPosition -1]

        defaulOptioonsView()
        if (mCurrentPosition == mQuestionList!!.size){
            biding.btnSiguiente.text = "Finalizado"


        }else {
            biding.btnSiguiente.text = "Enviar"

        }

        biding.progressBar.progress = mCurrentPosition
        biding.tvProgres.text = "$mCurrentPosition" + "/" + biding.progressBar.max

        biding.tvPregunta.text = questionTeorico!!.question
        biding.ivImage.setImageResource(questionTeorico.image)
        biding.tvPrimeraOpcion.text = questionTeorico.opcionUno
        biding.tvSegundaOpcion.text = questionTeorico.opcionDos
        biding.tvTerceraOpcion.text = questionTeorico.opcionTres
        biding.tvCuartaOpcion.text = questionTeorico.opcionCuatro


    }
    private  fun defaulOptioonsView(){
        val option = ArrayList<TextView>()
        option.add(0,biding.tvPrimeraOpcion)
        option.add(1,biding.tvSegundaOpcion)
        option.add(2,biding.tvTerceraOpcion)
        option.add(3,biding.tvCuartaOpcion)


        for (option in option){
        option.setTextColor(Color.parseColor("#7A8089"))
        option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )

        }

    }

    override fun onClick(v: View?) {

        when(v?.id){
         R.id.tv_primeraOpcion ->{
                SelectedOptionView(biding.tvPrimeraOpcion,1)

            }
            R.id.tv_segundaOpcion ->{
                SelectedOptionView(biding.tvSegundaOpcion,2)

            }
            R.id.tv_terceraOpcion ->{
                SelectedOptionView(biding.tvTerceraOpcion,3)

            }
            R.id.tv_cuartaOpcion ->{
                SelectedOptionView(biding.tvCuartaOpcion,4)

            }
            R.id.btn_siguiente ->{
                if (mSelectedOptionPosition == 0){
                    mCurrentPosition ++

                    when {
                        mCurrentPosition <= mQuestionList!!.size ->{
                        SetQuestion()

                        }else-> {

                        ResultTestStatics.CORRECT_ANSWERST = mCorrectAnswers.toString()
                        ResultTestStatics.TOTAL_QUESTIONT = mQuestionList!!.size.toString()
                        val intent = Intent (this,ResultadoActivity::class.java)
                        startActivity(intent)

                        }
                    }
                } else{
                    val question = mQuestionList?.get(mCurrentPosition -1)
                    if (question!!.RespuestaCorrecta != mSelectedOptionPosition){

                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)

                    } else{

                        mCorrectAnswers++
                    }

                    answerView(question.RespuestaCorrecta, R.drawable.correct_option_border_bg)

                if (mCurrentPosition == mQuestionList!!.size){

                    biding.btnSiguiente.text ="Terminado"

                } else{

                    biding.btnSiguiente.text = "Ir a la siguiente pregunta"

                }
                    mSelectedOptionPosition = 0

                }
            }
        }
    }

    private fun answerView(answers: Int, drawableView: Int){
        when(answers){
           1 ->{

               biding.tvPrimeraOpcion.background = ContextCompat.getDrawable(
                   this, drawableView

               )
           }

            2 ->{

                biding.tvSegundaOpcion.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }

            3 ->{

                biding.tvTerceraOpcion.background = ContextCompat.getDrawable(
                    this, drawableView
                )

            }
            4 ->{

                biding.tvCuartaOpcion.background = ContextCompat.getDrawable(
                    this, drawableView
                )

            }

        }


    }

    private fun SelectedOptionView(tv: TextView, selectedOptionNum: Int){

        defaulOptioonsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#364A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }


}