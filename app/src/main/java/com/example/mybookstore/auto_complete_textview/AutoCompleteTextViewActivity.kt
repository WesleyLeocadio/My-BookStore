package com.example.mybookstore.auto_complete_textview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.room.Room
import com.example.mybookstore.R
import com.example.mybookstore.connectionDB.AppDatabase
import kotlinx.android.synthetic.main.activity_auto_complete_text_view.*
import kotlinx.android.synthetic.main.activity_auto_complete_text_view.textYear
import kotlinx.android.synthetic.main.activity_register.*

class AutoCompleteTextViewActivity : AppCompatActivity() {
    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bd-wesley"
        )
            .allowMainThreadQueries()
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete_text_view)
        var livros = db.bookDao().listAll()

        var titulos = Array<String>(livros.size, {i -> i.toString()})
        for (i in 0 until  livros.size){
            titulos[i] = livros[i].name
        }


        var livroToListAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_expandable_list_item_1,
            titulos)

        autocomplete.setAdapter(livroToListAdapter)

        autocomplete.setOnItemClickListener { adapterView, view, i, l ->
            var selected = adapterView.getItemAtPosition(i)
            var livro=db.bookDao().findByName(selected.toString())
            textTitle.text = livro.name
            textAtor.text = livro.author
            textYear.text = livro.year.toString()
            nota.rating= livro.note
        }
    }
}
