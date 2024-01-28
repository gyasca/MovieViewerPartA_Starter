package it2161.assignment2.movieviewerparta_starter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import it2161.assignment2.movieviewerparta_starter.entity.SimpleMovieItem
import kotlinx.android.synthetic.main.activity_simple_item_detail.*

class SimpleItemDetailActivity : AppCompatActivity() {

    var movieItem: SimpleMovieItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_item_detail)

        // Back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Retrieve intent extras
        val title = intent.getStringExtra("selectedMovieTitle")
        val overview = intent.getStringExtra("selectedMovieOverview")
        val releaseDate = intent.getStringExtra("selectedMovieReleaseDate")
        val language = intent.getStringExtra("selectedMovieLanguage")

        tvTitleSpecific.text = title
        tvOverviewContentSpecific.text = overview
        tvReleaseDateContentSpecific.text = releaseDate
        tvLanguageContentSpecific.text = language

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.simpleitemdetail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // database handler
        val mm = MovieViewerApplication.appInstance

        val title = intent.getStringExtra("selectedMovieTitle")

        if (item.itemId == R.id.miAddAsFavorite) {
//            val intentToLogin = Intent(this,Login::class.java)
//            startActivity(intentToLogin)
            // Get the selected SimpleMovieItem

            // Check if a movie is selected
            if (title != null) {
                // Update the FAVORITE attribute in the database
                mm.updateMovieAsFavorite(title,true, applicationContext)
            }
        }

        return super.onOptionsItemSelected(item)
    }


}