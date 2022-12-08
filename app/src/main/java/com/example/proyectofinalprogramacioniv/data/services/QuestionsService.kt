package com.example.proyectofinalprogramacioniv.data.services

import com.example.proyectofinalprogramacioniv.data.datasources.FirestoreNetwork
import com.example.proyectofinalprogramacioniv.data.models.Questions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class QuestionsService:IQuestions {

    private val connectionFirebase = FirestoreNetwork.ProviderFirebaseFirestore()

    override suspend fun GetQuestions(): ArrayList<Questions> {

        var listQTheoretical = arrayListOf<Questions>()

        withContext(Dispatchers.IO){

            connectionFirebase
                .collection("Questions")
                //.whereEqualTo("test_name", "Teorico")
                .get().addOnSuccessListener { list ->

                    for (it in list){

                        var collectionQTheoretical = it.toObject(Questions::class.java)

                        listQTheoretical.add(collectionQTheoretical)
                    }

                }.await()
        }

        return listQTheoretical
    }

}