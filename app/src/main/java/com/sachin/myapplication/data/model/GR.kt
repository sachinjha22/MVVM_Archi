package com.sachin.myapplication.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

//Common Gson Response
@Keep
data class GR(
    @SerializedName("status")
    val s: String?,
    @SerializedName("message")
    val m: String?,
    @SerializedName("resultObject")
    val r: ArrayList<Any?>?,
) : Serializable
