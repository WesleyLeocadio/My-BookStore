package com.example.mybookstore.recycler_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.mybookstore.R
import com.example.mybookstore.connectionDB.AppDatabase
import com.example.mybookstore.domain.Book
import kotlinx.android.synthetic.main.activity_recycler.*
import androidx.recyclerview.widget.LinearLayoutManager


class RecyclerViewActivity : AppCompatActivity() {
    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "bd-wesley"
        )
            .allowMainThreadQueries()
            .build()
    }
    var livros:List<Book>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        var adapter = BookAdapterRecyclerView(this, db.bookDao().listAll())

        recyclerview.adapter=adapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerview.layoutManager = layout

    }
}
