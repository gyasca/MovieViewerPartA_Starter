package it2161.assignment2.movieviewerparta_starter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it2161.assignment2.movieviewerparta_starter.entity.SimpleMovieItem

class SimpleItemDetailActivity : AppCompatActivity() {

    var movieItem: SimpleMovieItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_item_detail)
    }
}