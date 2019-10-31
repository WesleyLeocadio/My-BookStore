package com.example.mybookstore.search_books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.room.Room
import com.example.mybookstore.R
import com.example.mybookstore.connectionDB.AppDatabase
import com.example.mybookstore.domain.Book
import kotlinx.android.synthetic.main.activity_search_books.*

class SearchBooksActivity : AppCompatActivity() {
    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bd-wesley")
            .allowMainThreadQueries()
            .build()
    }

    var books = ArrayList<Book>()
    var cont = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_books)
        setBooks()

        newBook()

        btnDireita.setOnClickListener {
            cont++
            newBook()
        }

        btnEsquerda.setOnClickListener {
            cont--
            newBook()
        }
        btnDelete.setOnClickListener {
            Log.i("cont","${books.get(cont)}")
            db.bookDao().deletar(books.get(cont))
            setBooks()


        }


    }
    fun setBooks(){
        books.clear()
        db.bookDao().listAll().forEach {
            books.add(it)

        }
        newBook()

    }

    fun newBook() {
        if (books.size > 0) {
            textTitle.text = books.get(cont).name
            textAutor.text = books.get(cont).author
            textYear.text=books.get(cont).year.toString()
            textNote.text = books.get(cont).note.toString()
            btnDireita.visibility = View.VISIBLE
            btnEsquerda.visibility = View.VISIBLE
            btnDelete.visibility= View.VISIBLE
            btnEdit.visibility= View.VISIBLE

            checkLenght()
        } else {
            btnDireita.visibility = View.INVISIBLE
            btnEsquerda.visibility = View.INVISIBLE
            btnDelete.visibility= View.INVISIBLE
            btnEdit.visibility= View.INVISIBLE
            clear()

        }
    }
    fun checkLenght() {
        if (cont + 1 >= books.size) {
            btnDireita.visibility = View.INVISIBLE
        } else {
            btnEsquerda.visibility = View.VISIBLE
        }


        if (cont - 1 < 0 ) {
            btnEsquerda.visibility = View.INVISIBLE
        }
    }
    fun clear(){
        textTitle.text = ""
        textAutor.text = ""
        textYear.text = ""
        textNote.text = ""
    }
}
