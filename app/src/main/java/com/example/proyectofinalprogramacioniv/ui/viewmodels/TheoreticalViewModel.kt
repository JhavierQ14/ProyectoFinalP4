package com.example.proyectofinalprogramacioniv.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalprogramacioniv.data.models.Questions
import com.example.proyectofinalprogramacioniv.domain.GetQuestionsTheoretical
import kotlinx.coroutines.launch

class TheoreticalViewModel: ViewModel() {

    val questionsModel = MutableLiveData<Questions>()
    val isLoading = MutableLiveData<Boolean>()
    var getQuestionsTheoretical = GetQuestionsTheoretical()

    fun onCreate() {

        viewModelScope.launch {

            isLoading.postValue(true)
            val result = getQuestionsTheoretical()

            if (!result.isNullOrEmpty()){

                questionsModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }
}