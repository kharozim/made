package com.ozimos.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("status_message")
    val statusMessage: String? = null,

    @SerializedName("status_code")
    val statusCode: Int? = null,

    @SerializedName("results")
    val data: T? = null
)