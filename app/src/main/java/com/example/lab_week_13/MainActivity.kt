package com.example.lab_week_13

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lab_week_13.databinding.ActivityMainBinding
import com.example.lab_week_13.model.DetailsActivity
import com.example.lab_week_13.model.Movie
import com.example.lab_week_13.model.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Setup Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 2. Setup RecyclerView & Adapter
        val adapter = MovieAdapter()
        binding.rvMovies.layoutManager = GridLayoutManager(this, 2)
        binding.rvMovies.adapter = adapter

        // 3. Ambil Repository dari Application Class (Sesuai Week 12)
        val movieRepository = (application as MovieApplication).movieRepository

        // 4. Inisialisasi ViewModel dengan Factory (Sesuai Week 12)
        val movieViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MovieViewModel(movieRepository) as T
            }
        })[MovieViewModel::class.java]

        // 5. Hubungkan ViewModel ke XML Binding
        binding.viewModel = movieViewModel
        binding.lifecycleOwner = this

        // 6. Setup Click Listener
        adapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Movie) {
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra(DetailsActivity.EXTRA_TITLE, data.title)
                intent.putExtra(DetailsActivity.EXTRA_RELEASE, data.release_date)
                intent.putExtra(DetailsActivity.EXTRA_OVERVIEW, data.overview)
                intent.putExtra(DetailsActivity.EXTRA_POSTER, data.poster_path)
                startActivity(intent)
            }
        })
    }
}