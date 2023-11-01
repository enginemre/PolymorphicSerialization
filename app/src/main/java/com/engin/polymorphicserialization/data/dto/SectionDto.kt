package com.engin.polymorphicserialization.data.dto

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Polymorphic
@Serializable
sealed class Section{
    abstract val type : String

    @Serializable
    @SerialName("info")
    data class Info(
        override val type: String,
        val info : String
    ) : Section()

    @Serializable
    @SerialName("article")
    data class Articles(
        override val type : String,
        val title : String,
        val description : String
    ) :Section()

    @Serializable
    @SerialName("image")
    data class Image(
        override val type : String,
        val imageUrl : String
    ) :Section()

}
