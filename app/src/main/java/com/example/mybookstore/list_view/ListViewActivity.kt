package com.example.mybookstore.list_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.mybookstore.R
import com.example.mybookstore.connectionDB.AppDatabase
import com.example.mybookstore.domain.Book
import com.example.mybookstore.view_holder.BookAdapterViewHolder
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {
    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bd-wesley"
        )
            .allowMainThreadQueries()
            .build()
    }

    var listBook:List<Book>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        listBook = db.bookDao().listAll()
        listview.adapter = BookAdapterViewHolder(this, listBook as List<Book>)
        listview.setOnItemClickListener{adapterView, view, i, l ->
            var frutaSelecionada = listBook?.get(i)
            Toast.makeText(this, "${frutaSelecionada?.name} id=${frutaSelecionada?.id}", Toast.LENGTH_SHORT).show()
        }


    }
}
