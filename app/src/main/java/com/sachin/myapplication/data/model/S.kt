package com.sachin.myapplication.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
enum class S {
    @SerializedName("SUCCESS")
    SUCCESS,

    @SerializedName("ERROR")
    ERROR,

    @SerializedName("LOADING")
    LOADING
}
