package com.example.lab_week_13.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_week_13.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> = _popularMovies

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    init {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            Log.d("API_DEBUG", "ViewModel: Memulai request ke Repository...")

            movieRepository.fetchMovies()
                .catch { e ->
                    Log.e("API_ERROR", "Gagal mengambil data: ${e.message}", e)

                    _error.value = "An exception occurred: ${e.message}"
                }
                .collect { movies ->
                    Log.d("API_DEBUG", "ViewModel: Data diterima dari Repository. Jumlah film: ${movies.size}")

                    val sortedMovies = movies.sortedByDescending { it.popularity }

                    _popularMovies.value = sortedMovies
                }
        }
    }
}