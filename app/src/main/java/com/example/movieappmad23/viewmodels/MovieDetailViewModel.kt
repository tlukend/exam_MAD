package com.example.movieappmad23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// inherit from ViewModel class
class MovieDetailViewModel(private val repository: MovieRepository, private val id: Long): ViewModel() {
    val movieState = MutableStateFlow(Movie())
    //val movieListState: StateFlow<List<Movie>> = _movieListState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getById(id)
                .collect{ movie ->
                    movie?.let {
                        movieState.value = movie
                    }
                }
        }
    }

    suspend fun updateFavoriteMovies(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        repository.updateMovie(movie)
    }
}