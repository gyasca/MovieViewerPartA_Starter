package it2161.assignment2.movieviewerparta_starter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.Toast
import it2161.assignment2.movieviewerparta_starter.MovieViewerApplication.Companion.movieItemArrays
import it2161.assignment2.movieviewerparta_starter.data.SimpleMovieSampleData
import kotlinx.android.synthetic.main.activity_simple_view_list_of_movies.movielist

class SimpleViewListOfMoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_view_list_of_movies)

        val mm = MovieViewerApplication.appInstance

        val arrayOfMoviesFromDatabase = mm.retrieveAllMovies(applicationContext)
        if (arrayOfMoviesFromDatabase.isEmpty()) {
            // Insert data from SimpleMovieSampleData into the database
            val sampleData = SimpleMovieSampleData.simpleMovieitemArray
            for (movieItem in sampleData) {
                // note: the line below the comments i passed in movieItem,
                // the individual attributes of movieItem is individually
                // saved into the database, handled by MovieViewerApplication.kt
                mm.addToDatabase(movieItem,applicationContext)
            }

            // attempt to retrieve movies again after inserting
            val updatedMoviesFromDatabase = mm.retrieveAllMovies(applicationContext)

            // check if movies are present and update UI
            if (updatedMoviesFromDatabase.isNotEmpty()) {
                val movieList = updatedMoviesFromDatabase.map {
                    "${it.title.toString()} - ${it.release_date.toString()}"
                }
                val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movieList)
                movielist.adapter = adapter

//                displayToast(updatedMoviesFromDatabase.toString())
            }

        } else {
            // retrieve movies from the database
            val movieList = arrayOfMoviesFromDatabase.map {
                "${it.title.toString()} - ${it.release_date.toString()}"}

            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movieList)
            movielist.adapter = adapter

//            displayToast(arrayOfMoviesFromDatabase.toString())
        }

        // Populate list from sample data on activity run
//        val movieList = SimpleMovieSampleData.simpleMovieitemArray.map {
//            "${it.title} - ${it.release_date}"
//        }



        // End of Populate list from sample data

//        val adapter = object : ArrayAdapter<SimpleMovieItem>(
//            this,
//            R.layout.single_movie_list_item_layout,
//            SimpleMovieSampleData.simpleMovieitemArray
//        ) {}
//
//        movielist.adapter = adapter
//
//        // Set data for each item directly using synthetic imports
//        adapter.notifyDataSetChanged()
//        movielist.setOnItemClickListener { _, _, position, _ ->
//            val selectedMovie = adapter.getItem(position)
//            // Handle the selected movie as needed
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.simpleviewlistofmovies, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun displayToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}