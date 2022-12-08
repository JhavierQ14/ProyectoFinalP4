package com.example.proyectofinalprogramacioniv.data.models

data class Questions(
    var cod_state_test: Int = 0,
    var test_name: String = "",
    var description_question: String = "",
    var img_question: String = "",
    var answer_question: ArrayList<String> = arrayListOf<String>("","","","","")
    )
