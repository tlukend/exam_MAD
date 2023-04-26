package com.example.movieappmad23.common

import android.content.Context
import com.example.movieappmad23.data.MovieDatabase
import com.example.movieappmad23.repositories.MovieRepository
import com.example.movieappmad23.viewmodels.AddMovieViewModelFactory
import com.example.movieappmad23.viewmodels.FavoritesViewModelFactory
import com.example.movieappmad23.viewmodels.MovieDetailViewModelFactory
import com.example.movieappmad23.viewmodels.MoviesViewModelFactory

object InjectorUtils {
    private fun getMovieRepository(context: Context): MovieRepository{
        return MovieRepository.getInstance(MovieDatabase.getDatabase(context.applicationContext).movieDao())
    }

    fun provideMovieDetailViewModelFactory(context: Context, movieId: Long): MovieDetailViewModelFactory{
        val repository = getMovieRepository(context)
        return MovieDetailViewModelFactory(repository, movieId)
    }

    fun provideFavoritesViewModelFactory(context: Context): FavoritesViewModelFactory{
        val repository = getMovieRepository(context)
        return FavoritesViewModelFactory(repository)
    }

    fun provideMoviesViewModelFactory(context: Context): MoviesViewModelFactory{
        val repository = getMovieRepository(context)
        return MoviesViewModelFactory(repository)
    }

    fun provideAddMovieViewModelFactory(context: Context): AddMovieViewModelFactory{
        val repository = getMovieRepository(context)
        return AddMovieViewModelFactory(repository)
    }
}