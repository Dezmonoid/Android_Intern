package com.example.android_intern


import com.google.gson.annotations.SerializedName

data class Episode(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<Result?>?
) {
    data class Info(
        @SerializedName("count")
        val count: Int?,
        @SerializedName("next")
        val next: String?,
        @SerializedName("pages")
        val pages: Int?,
        @SerializedName("prev")
        val prev: Any?
    )

    data class Result(
        @SerializedName("air_date")
        val airDate: String?,
        @SerializedName("characters")
        val characters: List<String?>?,
        @SerializedName("created")
        val created: String?,
        @SerializedName("episode")
        val episode: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("url")
        val url: String?
    )
}