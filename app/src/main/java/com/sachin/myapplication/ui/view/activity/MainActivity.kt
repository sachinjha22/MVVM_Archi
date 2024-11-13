package com.sachin.myapplication.ui.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.sachin.myapplication.data.model.S
import com.sachin.myapplication.data.model.UM
import com.sachin.myapplication.ui.theme.MyApplicationTheme
import com.sachin.myapplication.ui.viewmodel.UserViewModel
import com.sachin.myapplication.util.Constants
import com.sachin.myapplication.util.fromJson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val userViewModel by viewModels<UserViewModel>()

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            if (userViewModel.users.value.isNullOrEmpty()) {
                                getApi()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "you have already user",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }, Modifier.padding(innerPadding)) {
                            Text(text = "call Api")
                        }
                    }
                }
            }
        }

        userViewModel.users.observe(this) { list ->
            list?.let {
                userViewModel.insertUser(list as ArrayList<UM>)
                /// update your list here
            }
        }
    }

    private fun getApi() {
        lifecycleScope.launch {
            userViewModel.getSignUps().observe(this@MainActivity) {
                when (it.status) {
                    S.SUCCESS -> {
                        it.data?.let { res ->
                            try {
                                if (res.s!!.startsWith(Constants.SUCCESS_CODE)) {
                                    afterResponseUser(res.r)
                                } else {
                                    //// handle not success
                                }
                            } catch (e: Exception) {
                                //// handle any exceptions
                            }
                        } ?: run {
                            //// handle any exceptions
                        }
                    }

                    S.ERROR -> {
                        /// handle in case of error
                    }

                    S.LOADING -> {
                        //// handle screen on api loading
                    }
                }
            }
        }
    }

    private fun afterResponseUser(r: ArrayList<Any?>?) {
        if (!r.isNullOrEmpty()) {
            val list: ArrayList<UM>? = Gson().fromJson(Gson().toJson(r))
            list?.let {
                userViewModel._users.value = list
            }.run {
                //empty and nul list handling }
            }
        }
    }
}
