package com.example.booksearchcompany.bestseller.model


import com.google.gson.annotations.SerializedName

data class SeriesInfo(
    @SerializedName("seriesId")
    val seriesId: Int?,
    @SerializedName("seriesLink")
    val seriesLink: String?,
    @SerializedName("seriesName")
    val seriesName: String?
)