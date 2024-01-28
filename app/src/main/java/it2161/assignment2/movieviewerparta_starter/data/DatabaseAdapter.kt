package it2161.assignment2.movieviewerparta_starter.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseAdapter(c: Context) {

    private val DATABASE_NAME = "movies.db"
    private val DATABASE_TABLE = "MovieDBTable"
    private val DATABASE_VERSION = 1
    private var _db: SQLiteDatabase? = null
    private val context: Context?= null

    val KEY_ID = "_id"
    val COLUMN_KEY_ID = 0
    val OVERVIEW = "overview"
    val COLUMN_OVERVIEW_ID = 1
    val RELEASE_DATE = "release_date"
    val COLUMN_RELEASE_DATE_ID = 2
    val ORIGINAL_LANGUAGE = "original_language"
    val COLUMN_ORIGINAL_LANGUAGE_ID = 3
    val TITLE = "title"
    val COLUMN_TITLE_ID = 4

//    protected val DATABASE_CREATE = ("create table " + DATABASE_TABLE + " " + "(" + KEY_ID + " integer primary key autoincrement, " + OVERVIEW + " text not null);"
//            + RELEASE_DATE + " text not null);" + ORIGINAL_LANGUAGE + " text not null);" + TITLE + " text not null);")

    protected val DATABASE_CREATE = (
            "create table " + DATABASE_TABLE + " (" +
                    KEY_ID + " integer primary key autoincrement, " +
                    OVERVIEW + " text not null, " +
                    RELEASE_DATE + " text not null, " +
                    ORIGINAL_LANGUAGE + " text not null, " +
                    TITLE + " text not null);"
            )

    private val MYDBADAPTER_LOG_CAT = "MY_LOG"

    private var dbHelper: SimpleMovieDbHelper? = null

    init {
        //TODO 1 : Create a MyDBOpenHelper object
        dbHelper = SimpleMovieDbHelper(c, DATABASE_NAME, DATABASE_VERSION)
    }

    fun close() {
        //TODO 2 : close the table using the SQLite database handler
        _db?.close()
    }


    fun open() {
        //TODO 3 : Open DB using the appropriate methods
        try {
            _db = dbHelper?.getWritableDatabase()
        } catch (e: SQLiteException) {
            _db = dbHelper?.getReadableDatabase()
        }

    }

    fun insertEntry(overview: String, releaseDate: String, originalLanguage: String, title: String): Long? {
        //TODO 4 - insert record into table

        val newEntryValues = ContentValues()

        newEntryValues.put(OVERVIEW, overview)
        newEntryValues.put(RELEASE_DATE, releaseDate)
        newEntryValues.put(ORIGINAL_LANGUAGE, originalLanguage)
        newEntryValues.put(TITLE, title)

        return _db?.insert(DATABASE_TABLE,null,newEntryValues)
    }

    fun removeEntry(_rowIndex: Int): Boolean {
        //TODO 5 - remove record from table
        if (_db!!.delete(DATABASE_TABLE,KEY_ID + " = " + _rowIndex,null ) <= 0) {

            Log.w(MYDBADAPTER_LOG_CAT, "Removing entry where id = $_rowIndex failed")
            return false
        }

        return true
    }

    fun updateEntry(_rowIndex: Int, entryName: String, entryTel: String): Boolean {


        return false
    }

    fun retrieveAllEntriesCursor(): Cursor? {
        //TODO 6 - retrieve all records from table

        var c: Cursor? = null
        try {
            c = _db?.query(DATABASE_TABLE,
                arrayOf(KEY_ID, OVERVIEW, RELEASE_DATE, ORIGINAL_LANGUAGE, TITLE),
                null,
                null,
                null,
                null,
                null)
        } catch (e: SQLiteException) {

            Log.w(DATABASE_TABLE, "Retrieve fail")
        }

        return c
    }



    inner class SimpleMovieDbHelper(c: Context, db_name : String, ver_no : Int ): SQLiteOpenHelper(c, db_name, null, ver_no){


        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(DATABASE_CREATE)
            Log.w(MYDBADAPTER_LOG_CAT, "HELPER : DB $DATABASE_TABLE created!")

        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }
    }

}