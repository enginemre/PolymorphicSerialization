package com.engin.polymorphicserialization.data.dto

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Section{

    @Serializable
    @SerialName("info")
    data class Info(
        val info : String
    ) : Section()

    @Serializable
    @SerialName("article")
    data class Articles(
        val title : String,
        val description : String
    ) :Section()

    @Serializable
    @SerialName("image")
    data class Image(
        val imageUrl : String
    ) :Section()

}
