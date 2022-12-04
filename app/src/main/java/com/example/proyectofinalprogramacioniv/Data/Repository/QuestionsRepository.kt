package com.example.proyectofinalprogramacioniv.Data.Repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.proyectofinalprogramacioniv.Data.DataSources.FirebaseConnectionDS
import com.example.proyectofinalprogramacioniv.Data.Entities.Answers
import com.example.proyectofinalprogramacioniv.Data.Entities.Questions
import com.example.proyectofinalprogramacioniv.Data.Services.QuestionsService

class QuestionsRepository : QuestionsService {

    private lateinit var _firebaseConnecion: FirebaseConnectionDS
    companion object{

        const val QCOLLECTION = "Questions"
    }

    private lateinit var listQuestionT: ArrayList<Questions>
    override fun GetQuestionsTheoretical():ArrayList<Questions> {
        TODO("Not yet implemented")

        var collection = mutableListOf<Questions>()
        this._firebaseConnecion.ProviderFirebaseFirestore()
            .collection(QCOLLECTION)
            .whereEqualTo("test_name","Teorico")
            .whereEqualTo("cod_state_test","1")
            .get()
            .addOnSuccessListener {

                for ( list in it){

                    Log.d(TAG, "${list.id} => ${list.data}")
                }
            }.addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

        return listQuestionT
    }

}