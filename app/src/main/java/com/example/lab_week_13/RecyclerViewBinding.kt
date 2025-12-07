package com.example.lab_week_13

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_13.model.Movie

@BindingAdapter("list")
fun bindMovies(view: RecyclerView, movies: List<Movie>?) {
    if (view.adapter == null) {
        view.adapter = MovieAdapter()
    }

    val adapter = view.adapter as? MovieAdapter

    if (movies == null) {
        Log.d("BINDING_DEBUG", "Movies list is NULL")
    } else {
        Log.d("BINDING_DEBUG", "Movies list received. Size: ${movies.size}")
        adapter?.addMovies(movies)
    }
}