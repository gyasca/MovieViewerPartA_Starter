package it2161.assignment2.movieviewerparta_starter

import android.app.Application
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


}