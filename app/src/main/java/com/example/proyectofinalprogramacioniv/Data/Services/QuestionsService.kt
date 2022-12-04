package com.example.proyectofinalprogramacioniv.Data.Services

import com.example.proyectofinalprogramacioniv.Data.Entities.Answers
import com.example.proyectofinalprogramacioniv.Data.Entities.Questions

interface QuestionsService {

    fun GetQuestionsTheoretical(): ArrayList<Questions>
}