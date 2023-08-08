package com.example.android_intern


import com.google.gson.annotations.SerializedName

data class WeatherResponse (

    @SerializedName("coord")
    var coord: Coord? = null,

    @SerializedName("sys")
    var sys: Sys? = null,

    @SerializedName("weather")
    var weather: List<Weather>,

    @SerializedName("main")
    var main: Main? = null,

    @SerializedName("wind")
    var wind: Wind? = null,

    @SerializedName("rain")
    var rain: Rain? = null,

    @SerializedName("clouds")
    var clouds: Clouds? = null,

    @SerializedName("dt")
    var dt: Float = 0.toFloat(),

    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("cod")
    var cod: Float = 0.toFloat()
)

    data class Weather(
        @SerializedName("id")
        var id: Int = 0,

        @SerializedName("main")
        var Main: String? = null,

        @SerializedName("description")
        var description: String? = null,

        @SerializedName("icon")
        var icon: String? = null,
    )

    data class Clouds(
        @SerializedName("all")
        var all: Float = 0.toFloat()
    )

    data class Rain(
        @SerializedName("3h")
        var h3: Float = 0.toFloat()
    )

    data class Wind(
        @SerializedName("speed")
        var speed: Float = 0.toFloat(),

        @SerializedName("deg")
        var deg: Float = 0.toFloat()
    )

    data class Main(
        @SerializedName("temp")
        var temp: Float = 0.toFloat(),

        @SerializedName("humidity")
        var humidity: Float = 0.toFloat(),

        @SerializedName("pressure")
        var pressure: Float = 0.toFloat(),

        @SerializedName("temp_min")
        var temp_min: Float = 0.toFloat(),

        @SerializedName("temp_max")
        var temp_max: Float = 0.toFloat()
    )

    data class Sys(
        @SerializedName("country")
        var country: String? = null,

        @SerializedName("sunrise")
        var sunrise: Long = 0,

        @SerializedName("sunset")
        var sunset: Long = 0
    )

    data class Coord(
        @SerializedName("lon")
        var lon: Float = 0.toFloat(),

        @SerializedName("lat")
        var lat: Float = 0.toFloat()
    )

data class Weathers(
    val weather: List<WeatherResponse>
)