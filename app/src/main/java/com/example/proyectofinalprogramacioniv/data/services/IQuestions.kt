package com.example.proyectofinalprogramacioniv.data.services

import com.example.proyectofinalprogramacioniv.data.models.Questions

interface IQuestions {

   suspend fun GetQuestions(): ArrayList<Questions>
}