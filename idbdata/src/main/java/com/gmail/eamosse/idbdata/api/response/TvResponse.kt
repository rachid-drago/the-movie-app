package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Tv
import com.google.gson.annotations.SerializedName

internal data class TvResponse(
    @SerializedName("results")
    val results: List<Result>,
) {
    data class Result(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("vote_average")
        val vote: String,
        @SerializedName("first_air_date")
        val date: String,
        @SerializedName("backdrop_path")
        val poster: String,
        @SerializedName("overview")
        val overview: String
    )
}

internal fun TvResponse.Result.toTv() = Tv(
    id = id,
    name = name,
    vote = vote,
    date = date,
    poster = "https://image.tmdb.org/t/p/w185$poster",
    overview = overview
)
