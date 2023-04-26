package com.example.movieappmad23.data

import androidx.room.*
import com.example.movieappmad23.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert
    suspend fun add(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("SELECT * from movie")
    fun getAll(): Flow<List<Movie>>

    @Query("SELECT * from movie WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<Movie>>

    @Query("SELECT * from movie WHERE dbId =:id")
    fun get(id: Long): Flow<Movie>

    @Query("DELETE from movie")
    suspend fun deleteAll()
}