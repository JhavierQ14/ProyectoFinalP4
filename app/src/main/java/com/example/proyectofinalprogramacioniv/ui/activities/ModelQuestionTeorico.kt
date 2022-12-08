package com.example.proyectofinalprogramacioniv.ui.activities

data class ModelQuestionTeorico (

    val id: Int,
    val question: String,
    val image: Int = 0,
    val opcionUno:String,
    val opcionDos:String,
    val opcionTres:String,
    val opcionCuatro:String,
    val RespuestaCorrecta: Int


        )
