package com.example.movieappmad23.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.movieappmad23.common.Validator
import com.example.movieappmad23.repositories.MovieRepository
import com.example.movieappmad23.screens.AddMovieUIEvent
import com.example.movieappmad23.screens.AddMovieUiState
import com.example.movieappmad23.screens.hasError
import com.example.movieappmad23.screens.toMovie

class AddMovieViewModel(private val repository: MovieRepository): ViewModel() {

    var movieUiState by mutableStateOf(AddMovieUiState())
        private set


    fun updateUIState(newMovieUiState: AddMovieUiState, event: AddMovieUIEvent){
        var state = AddMovieUiState()   // this is needed because copy always creates a new instance

        when (event) {
            is AddMovieUIEvent.TitleChanged -> {
                val titleResult = Validator.validateMovieTitle(newMovieUiState.title)
                state = if(!titleResult.successful) newMovieUiState.copy(titleErr = true) else newMovieUiState.copy(titleErr = false)
            }
            is AddMovieUIEvent.YearChanged -> {
                val yearResult = Validator.validateMovieYear(newMovieUiState.year)
                state = if(!yearResult.successful) newMovieUiState.copy(yearErr = true) else newMovieUiState.copy(yearErr = false)
            }

            is AddMovieUIEvent.DirectorChanged -> {
                val directorResult = Validator.validateMovieDirector(newMovieUiState.director)
                state = if(!directorResult.successful) newMovieUiState.copy(directorErr = true) else newMovieUiState.copy(directorErr = false)
            }

            is AddMovieUIEvent.ActorsChanged -> {
                val actorsResult = Validator.validateMovieActors(newMovieUiState.actors)
                state = if(!actorsResult.successful) newMovieUiState.copy(actorsErr = true) else newMovieUiState.copy(actorsErr = false)
            }

            is AddMovieUIEvent.RatingChanged -> {
                val ratingResult = Validator.validateMovieRating(newMovieUiState.rating)
                state = if(!ratingResult.successful) newMovieUiState.copy(ratingErr = true) else newMovieUiState.copy(ratingErr = false)
            }

            is AddMovieUIEvent.GenresChanged -> {
                val genreResult = Validator.validateMovieGenres(newMovieUiState.genre)
                state = if(!genreResult.successful) newMovieUiState.copy(genreErr = true) else newMovieUiState.copy(genreErr = false)
            }

            is AddMovieUIEvent.PlotChanged -> {
                state = newMovieUiState
            }
            else -> {}
        }

        movieUiState = state.copy(actionEnabled = !newMovieUiState.hasError())
    }

    suspend fun saveMovie() {
        val movie = movieUiState.toMovie()
        repository.addMovie(movie)
    }
}