package com.sachin.myapplication.util

import android.text.TextUtils
import com.google.gson.Gson
import com.sachin.myapplication.data.model.GR
import com.sachin.myapplication.data.model.S
import com.sachin.myapplication.data.model.S.*
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

data class Resource<out T>(val status: S, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            try {
                if (data != null) {
                    Gson().fromJson(Gson().toJson(data), GR::class.java)
                        ?: return Resource(
                            status = ERROR,
                            data = data,
                            message = "Looks like something went wrong. We’ll be back soon! Meanwhile, feel free to contact us at 8700758072."
                        )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return Resource(status = SUCCESS, data = data, message = null)
        }

        fun <T> error(data: T?, message: String, exception: Exception?): Resource<T> {
            var msgUpdate = message
            try {
                if (exception != null) {
                    if (exception is SocketTimeoutException) {
                        msgUpdate = Constants.SOCKET_TIME_OUT
                    } else if (exception is ConnectException || exception is UnknownHostException) {
                        msgUpdate = Constants.NO_INTERNET
                    } else if (!TextUtils.isEmpty(message) && (message.contains("Failed to connect")
                                || message.contains("Unable to resolve host"))
                    ) {
                        msgUpdate = Constants.NO_INTERNET
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return Resource(status = ERROR, data = data, message = msgUpdate)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(status = LOADING, data = data, message = null)
        }
    }
}