package com.sachin.myapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.sachin.myapplication.data.model.UM
import com.sachin.myapplication.data.repository.ServerRepository
import com.sachin.myapplication.data.repository.UserRepository
import com.sachin.myapplication.util.Constants
import com.sachin.myapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: ServerRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val _users = MutableLiveData<List<UM?>?>()
    val users: LiveData<List<UM?>?> = _users

    init {
        _users.value = ArrayList()
    }

    fun insertUser(user: ArrayList<UM>) =
        viewModelScope.launch {
            userRepository.insertUsers(user)
        }


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