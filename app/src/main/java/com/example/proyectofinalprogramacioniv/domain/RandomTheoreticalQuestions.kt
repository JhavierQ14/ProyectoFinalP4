package com.example.proyectofinalprogramacioniv.domain

import com.example.proyectofinalprogramacioniv.data.localdb.QuestionsProvider
import com.example.proyectofinalprogramacioniv.data.models.Questions
import com.example.proyectofinalprogramacioniv.data.repository.QuestionsRepository

class RandomTheoreticalQuestions {

    //
    private val repositoryQuestions = QuestionsRepository()

    operator fun invoke(): ArrayList<Questions>?{

        val listQuestions = QuestionsProvider.questions
        var ramdomList = arrayListOf<Questions>()

        if(!listQuestions.isNullOrEmpty()){

            for (i in 0..29){

                var randomSize = (0..49).random()
                var list = listQuestions[randomSize]
                ramdomList.add(list)
            }
        }

        return ramdomList
    }
}