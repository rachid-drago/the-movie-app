package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Film
import com.google.gson.annotations.SerializedName

data class FilmResponse(
    @SerializedName("results")
    val films: List<Film>
)
{
    data class Film(

        @SerializedName("id")
        val id: String,

        @SerializedName("original_title")
        val name: String,

        @SerializedName("overview")
         val description: String,

        @SerializedName("poster_path")
        val poster_path: String
    )
}

internal fun FilmResponse.Film.toFilm() = Film(
    id = id,
    name = name ,
    description = description,
    poster_path =  "https://image.tmdb.org/t/p/original" + poster_path
)
