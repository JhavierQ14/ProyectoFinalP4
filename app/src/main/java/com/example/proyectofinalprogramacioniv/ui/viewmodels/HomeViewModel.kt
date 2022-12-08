package com.example.proyectofinalprogramacioniv.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalprogramacioniv.data.models.Questions
import com.example.proyectofinalprogramacioniv.domain.GetQuestionsTheoretical
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    //
    val questionModel = MutableLiveData<Questions>()

    var oGetQuestionsTheoretical = GetQuestionsTheoretical()

    fun onCreate(){

        viewModelScope.launch {

            val listQuestions: ArrayList<Questions>? = oGetQuestionsTheoretical()

            if (!listQuestions.isNullOrEmpty()){

                questionModel.postValue(listQuestions[0])
            }
        }
    }
}