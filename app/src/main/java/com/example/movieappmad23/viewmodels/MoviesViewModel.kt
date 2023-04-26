package com.example.movieappmad23.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

// inherit from ViewModel class
class MoviesViewModel(private val repository: MovieRepository): ViewModel() {
    private val _movieListState = MutableStateFlow(listOf<Movie>())
    val movieListState: StateFlow<List<Movie>> = _movieListState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllMovies().distinctUntilChanged()
                .collect{ listOfMovies ->
                    if(listOfMovies.isNullOrEmpty()){
                        Log.d("MoviesViewModel", "Empty movies")
                    } else {
                        _movieListState.value = listOfMovies
                    }
                }
        }
    }

    suspend fun getMovieById(dbId: Long?) = movieListState.value.filter { it.dbId == dbId }

    suspend fun updateFavoriteMovies(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        repository.updateMovie(movie)
    }
}