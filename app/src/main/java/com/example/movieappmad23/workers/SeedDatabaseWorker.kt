package com.example.movieappmad23.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.movieappmad23.data.MovieDatabase
import com.example.movieappmad23.models.getMovies
import kotlinx.coroutines.coroutineScope

/**
 * Class that seeds the database at first app start
 */
class SeedDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            val database = MovieDatabase.getDatabase(applicationContext)
            populateDatabase(database)
            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }

    private suspend fun populateDatabase(database: MovieDatabase){
        val dao = database.movieDao()

        dao.deleteAll()
        getMovies().forEach{
            dao.add(it)
        }
    }
}