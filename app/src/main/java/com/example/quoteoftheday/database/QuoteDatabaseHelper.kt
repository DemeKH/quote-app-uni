package com.example.quoteoftheday.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.quoteoftheday.Quote

class QuoteDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "quotes.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS favorites (id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT, author TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS favorites")
        onCreate(db)
    }

    fun addQuote(quote: Quote) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("text", quote.text)
            put("author", quote.author)
        }
        db.insert("favorites", null, values)
    }

    fun getAllQuotes(): List<Quote> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM favorites", null)
        val list = mutableListOf<Quote>()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val text = cursor.getString(1)
                val author = cursor.getString(2)
                list.add(Quote(id, text, author))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return list
    }

    fun clearAllQuotes() {
        writableDatabase.execSQL("DELETE FROM favorites")
    }
}
