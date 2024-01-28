package it2161.assignment2.movieviewerparta_starter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_list_of_favorite_movies.lvFavouriteMovies

class ListOfFavoriteMoviesActivity : AppCompatActivity() {
    var adapter : SimpleViewListOfMoviesActivity.MovieListAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_favorite_movies)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // database handler
        val mm = MovieViewerApplication.appInstance
        val arrayOfMoviesFromDatabase = mm.retrieveFavoriteMovies(applicationContext)

        val movieList = arrayOfMoviesFromDatabase
        adapter = SimpleViewListOfMoviesActivity.MovieListAdapter(this, movieList)
        // assign this adapter to movie listview in list view activity layout file
        lvFavouriteMovies.adapter = adapter

    }
}