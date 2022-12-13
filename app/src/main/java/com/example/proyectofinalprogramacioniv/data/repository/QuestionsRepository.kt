package com.example.proyectofinalprogramacioniv.data.repository

import com.example.proyectofinalprogramacioniv.data.localdb.QuestionsProvider
import com.example.proyectofinalprogramacioniv.data.models.Questions
import com.example.proyectofinalprogramacioniv.data.services.QuestionsService

class QuestionsRepository {

    private val serviceQuestion = QuestionsService()

    suspend fun GetAllQuestions(){

        val listQuestionResponse: ArrayList<Questions> = serviceQuestion.GetQuestions()
        QuestionsProvider.questions = listQuestionResponse
    }
}