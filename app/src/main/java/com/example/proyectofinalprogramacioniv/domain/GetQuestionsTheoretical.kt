package com.example.proyectofinalprogramacioniv.domain

import com.example.proyectofinalprogramacioniv.data.models.Questions
import com.example.proyectofinalprogramacioniv.data.repository.QuestionsRepository

class GetQuestionsTheoretical {

    //
    private val repositoryQuestions = QuestionsRepository()

    //
    suspend operator fun invoke(): ArrayList<Questions>? = repositoryQuestions.GetAllQuestions()
}