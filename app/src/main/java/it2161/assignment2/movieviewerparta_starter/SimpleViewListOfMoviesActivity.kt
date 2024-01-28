package it2161.assignment2.movieviewerparta_starter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import it2161.assignment2.movieviewerparta_starter.data.SimpleMovieSampleData
import it2161.assignment2.movieviewerparta_starter.entity.SimpleMovieItem
import kotlinx.android.synthetic.main.activity_simple_view_list_of_movies.lvMovie

class SimpleViewListOfMoviesActivity : AppCompatActivity() {
    var adapter : MovieListAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_view_list_of_movies)

        // database handler
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
                val movieList = updatedMoviesFromDatabase
                // val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movieList)
                adapter = MovieListAdapter(this, movieList)
                // assign this adapter to movie listview in list view activity layout file
                lvMovie.adapter = adapter

//                displayToast(updatedMoviesFromDatabase.toString())
            }

        } else {
            // retrieve movies from the database
            val movieList = arrayOfMoviesFromDatabase

            // val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movieList)
            adapter = MovieListAdapter(this, movieList)
            // assign this adapter to movie listview in list view activity layout file
            lvMovie.adapter = adapter

//            displayToast(arrayOfMoviesFromDatabase.toString())
        }
        // start
        lvMovie.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Get the selected SimpleMovieItem
                val selectedMovieItem = parent?.adapter?.getItem(position) as SimpleMovieItem?

                // Intent to simple item detail activity
                val intentToItemDetail = Intent(
                    this@SimpleViewListOfMoviesActivity,
                    SimpleItemDetailActivity::class.java
                )

                // Pass the SimpleMovieItem attributes as extra, convert to string then put extra
                if (selectedMovieItem != null) {
                    intentToItemDetail.putExtra(
                        "selectedMovieTitle",
                        selectedMovieItem.title.toString()
                    )
                    intentToItemDetail.putExtra(
                        "selectedMovieOverview",
                        selectedMovieItem.overview.toString()
                    )
                    intentToItemDetail.putExtra(
                        "selectedMovieReleaseDate",
                        selectedMovieItem.release_date.toString()
                    )
                    intentToItemDetail.putExtra(
                        "selectedMovieLanguage",
                        selectedMovieItem.original_language.toString()
                    )
                }

                startActivity(intentToItemDetail)

            }
        }
        // end
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.simpleviewlistofmovies, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.miSignOut) {
            val intentToLogin = Intent(this,Login::class.java)
            startActivity(intentToLogin)
        }

        if (item.itemId == R.id.miViewFavorites) {
            val intentToListOfFavoriteMovies = Intent(this,ListOfFavoriteMoviesActivity::class.java)
            startActivity(intentToListOfFavoriteMovies)
        }

        return super.onOptionsItemSelected(item)
    }

    fun displayToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    class MovieListAdapter(context: Context, data: List<SimpleMovieItem>) : BaseAdapter() {


        internal val sList:ArrayList<SimpleMovieItem> ?= ArrayList(data)
        private val mInflater : LayoutInflater

        init {
            this.mInflater = LayoutInflater.from(context)
        }


        fun addAll(data: Collection<SimpleMovieItem>){

            sList?.addAll(data)

        }
        fun clear(){

            sList?.clear()

        }

        override fun getItem(p0: Int): Any? {

            return sList?.get(p0)
        }

        override fun getItemId(p0: Int): Long {

            return 0
        }

        override fun getCount(): Int {
            return if(sList == null) 0 else sList!!.size
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val v : View
            v = this.mInflater.inflate(R.layout.single_movie_list_item_layout, parent, false)
            val title: TextView = v.findViewById(R.id.tvTitle)
            //title.text = sList?.get(position)

            val releaseDate: TextView = v.findViewById(R.id.tvReleaseDate)
            //releaseDate.text = sList?.get(position)
            val movieItem = sList?.get(position)

            if (movieItem != null) {
                title.text = "${movieItem.title}"
            }

            if (movieItem != null) {
                releaseDate.text = "${movieItem.release_date}"
            }

            return v
        }
    }
}