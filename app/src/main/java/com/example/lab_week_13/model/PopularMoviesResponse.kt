package com.example.lab_week_13.model

import com.squareup.moshi.Json

data class PopularMoviesResponse(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<Movie>
)