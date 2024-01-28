package it2161.assignment2.movieviewerparta_starter

import android.app.Application
import android.content.Context
import android.database.Cursor
import it2161.assignment2.movieviewerparta_starter.data.DatabaseAdapter
import it2161.assignment2.movieviewerparta_starter.data.SimpleMovieSampleData
import it2161.assignment2.movieviewerparta_starter.entity.SimpleMovieItem

class MovieViewerApplication : Application(){


    companion object {

        var movieItemArrays: ArrayList<SimpleMovieItem>?=null
        val appInstance = MovieViewerApplication()

    }

    init {
        movieItemArrays = SimpleMovieSampleData.simpleMovieitemArray
    }

    fun addToDatabase(movie: SimpleMovieItem, c: Context): Long? {
        val db = DatabaseAdapter(c)
        db.open()

        // Extract relevant movie information
        val overview = movie.overview.toString()
        val releaseDate = movie.release_date.toString()
        val originalLanguage = movie.original_language.toString()
        val title = movie.title.toString()

        // Insert into the database
        val rowID = db.insertEntry(overview, releaseDate, originalLanguage, title)

        db.close()

        return rowID
    }

    fun deleteFromDatabase(movieId: Int, c: Context): Boolean {
        val db = DatabaseAdapter(c)
        db.open()

        // Delete from the database
        val updateStatus = db.removeEntry(movieId)

        db.close()

        return updateStatus
    }

    fun retrieveAllMovies(c: Context): List<SimpleMovieItem> {
        val myCursor: Cursor?
        val db = DatabaseAdapter(c)
        // movieItemArrays?.clear()
        val resultedDatabaseList = ArrayList<SimpleMovieItem>()
        db.open()

        // Retrieve all movies from the database
        myCursor = db.retrieveAllEntriesCursor()
        if (myCursor != null && myCursor!!.count > 0) {
            myCursor!!.moveToFirst()
            do {
                // Extract data from the cursor
                val overview = myCursor.getString(db.COLUMN_OVERVIEW_ID)
                val releaseDate = myCursor.getString(db.COLUMN_RELEASE_DATE_ID)
                val originalLanguage = myCursor.getString(db.COLUMN_ORIGINAL_LANGUAGE_ID)
                val title = myCursor.getString(db.COLUMN_TITLE_ID)

                // Create SimpleMovieItem object and add to the list
                val movie = SimpleMovieItem(overview, releaseDate, originalLanguage, title)
                resultedDatabaseList.add(movie)

            } while (myCursor.moveToNext())
        }

        db.close()
        return resultedDatabaseList
    }

}