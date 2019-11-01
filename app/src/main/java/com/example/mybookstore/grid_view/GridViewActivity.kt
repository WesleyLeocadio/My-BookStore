package com.example.mybookstore.grid_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.mybookstore.R
import com.example.mybookstore.connectionDB.AppDatabase
import com.example.mybookstore.domain.Book
import com.example.mybookstore.view_holder.BookAdapterViewHolder
import kotlinx.android.synthetic.main.activity_grid_view.*
import kotlinx.android.synthetic.main.activity_list_view.*

class GridViewActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_grid_view)

        listBook = db.bookDao().listAll()
        gridview.adapter = BookAdapterViewHolder(this, listBook as List<Book>)
        gridview.setOnItemClickListener{adapterView, view, i, l ->
            var frutaSelecionada = listBook?.get(i)
            Toast.makeText(this, "${frutaSelecionada?.name} id=${frutaSelecionada?.id}", Toast.LENGTH_SHORT).show()
        }

    }
}
