package com.example.lab_week_13.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    @Json(name = "poster_path") val poster_path: String?,
    @Json(name = "backdrop_path") val backdrop_path: String?,
    @Json(name = "vote_average") val vote_average: Double,
    @Json(name = "release_date") val release_date: String?,
    @Json(name = "popularity") val popularity: Double
)