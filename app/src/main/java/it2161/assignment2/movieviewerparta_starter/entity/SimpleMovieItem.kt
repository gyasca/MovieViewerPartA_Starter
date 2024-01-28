package it2161.assignment2.movieviewerparta_starter.entity

class SimpleMovieItem (
    var overview: String? = null,
    var release_date: String? = null, var original_language: String? = null,
    var title: String? = null
) {


    init {

        this.overview = overview
        this.release_date = release_date
        this.original_language = original_language
        this.title = title

    }


}