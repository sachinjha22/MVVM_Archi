package com.sachin.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sachin.myapplication.data.repository.ServerRepository
import com.sachin.myapplication.util.Constants
import com.sachin.myapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: ServerRepository
) : ViewModel() {

    fun getSignUps() = liveData(Dispatchers.IO, Constants.MAX_TIME_OUT) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = repository.getData()
                )
            )
        } catch (e: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = e.message ?: "Error Occurred!",
                    exception = Exception()
                )
            )
        }
    }
}