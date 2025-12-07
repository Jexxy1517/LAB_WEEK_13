package com.example.lab_week_13

import com.example.lab_week_13.api.MovieService
import com.example.lab_week_13.database.MovieDatabase
import com.example.lab_week_13.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(
    private val movieService: MovieService,
    private val movieDatabase: MovieDatabase
) {
    private val apiKey = "332881400db8ad5dc1ef6fc3e0ae0c37"

    fun fetchMovies(): Flow<List<Movie>> {
        return flow {
            val movieDao = movieDatabase.movieDao()
            val savedMovies = movieDao.getMovies()

            if (savedMovies.isEmpty()) {
                val response = movieService.getPopularMovies(apiKey)
                val movies = response.results
                movieDao.addMovies(movies)
                emit(movies)
            } else {
                emit(savedMovies)
            }
        }.flowOn(Dispatchers.IO)
    }
}