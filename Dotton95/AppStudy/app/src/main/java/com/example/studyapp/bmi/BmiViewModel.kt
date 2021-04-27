package com.example.studyapp.bmi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BmiViewModel : ViewModel() {
    val bmi = MutableLiveData<String>("")
    val result = MutableLiveData<String>("")
}