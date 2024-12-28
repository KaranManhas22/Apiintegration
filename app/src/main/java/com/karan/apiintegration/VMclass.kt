package com.karan.apiintegration

import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class VMclass : ViewModel() {
    var getRes: MutableLiveData<Sealedclass<ResponseModel>?> = MutableLiveData() //used to chenge values instantly with running data and autohit API
    val uRepo = Repo()
    fun getData() {
        getRes.value = null
        getRes.value = Sealedclass.Loading()
        viewModelScope.launch {
            try {
                val response = uRepo.userRepo()
                if (response.code() == 200) {
                    if (response.body() != null) {
                        getRes.setValue(Sealedclass.Success(response.body()))
                        Log.e("response", "getSuccess:${response.body()}")
                    }
                } else {
                    Log.e("response", "getFailure:${response.body()}")
                    getRes.value = Sealedclass.Error(response.message())
                }
            } catch (ae: Exception) {
                ae.printStackTrace()//it gives the snippet of the error
            }
        }
    }
}