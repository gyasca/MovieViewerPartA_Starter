package it2161.assignment2.movieviewerparta_starter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class SimpleViewListOfMoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_view_list_of_movies)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.simpleviewlistofmovies, menu)
        return super.onCreateOptionsMenu(menu)
    }
}