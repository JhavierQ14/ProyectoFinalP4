package com.example.proyectofinalprogramacioniv.Data.Entities

data class Questions(
    var cod_state_test: Int,
    var test_name: String,
    var description_question: String,
    var img_question: String,
    var answer_question: ArrayList<Answers>
    )
