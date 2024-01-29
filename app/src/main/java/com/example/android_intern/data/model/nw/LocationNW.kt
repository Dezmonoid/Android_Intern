package com.example.android_intern.data.model.nw


import com.google.gson.annotations.SerializedName

data class LocationNW(
    @SerializedName("country")
    val country: String?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("local_names")
    val localNames: LocalNames?,
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("state")
    val state: String?
) {
    data class LocalNames(
        @SerializedName("ascii")
        val ascii: String?,
        @SerializedName("be")
        val be: String?,
        @SerializedName("de")
        val de: String?,
        @SerializedName("en")
        val en: String?,
        @SerializedName("eo")
        val eo: String?,
        @SerializedName("feature_name")
        val featureName: String?,
        @SerializedName("fr")
        val fr: String?,
        @SerializedName("ko")
        val ko: String?,
        @SerializedName("lt")
        val lt: String?,
        @SerializedName("pl")
        val pl: String?,
        @SerializedName("ru")
        val ru: String?,
        @SerializedName("uk")
        val uk: String?
    )
}