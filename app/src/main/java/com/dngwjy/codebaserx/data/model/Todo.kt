package com.dngwjy.codebaserx.data.model

import com.google.gson.annotations.SerializedName

data class Todo (
    @SerializedName("completed")
    var completed: Boolean,
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("userId")
    var userId: Int
)