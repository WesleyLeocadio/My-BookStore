package com.example.mybookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybookstore.auto_complete_textview.AutoCompleteTextViewActivity
import com.example.mybookstore.grid_view.GridViewActivity
import com.example.mybookstore.list_view.ListViewActivity
import com.example.mybookstore.recycler_view.RecyclerViewActivity
import com.example.mybookstore.register.RegisterActivity
import com.example.mybookstore.search_books.SearchBooksActivity
import com.example.mybookstore.view_pager.PageViewAdapter
import com.example.mybookstore.view_pager.ViewPagerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnCadastrar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))

        }

        btnSearchBook.setOnClickListener {
            startActivity(Intent(this, SearchBooksActivity::class.java))

        }

        btnAutoComplete.setOnClickListener {
            startActivity(Intent(this, AutoCompleteTextViewActivity::class.java))

        }

        btnListView.setOnClickListener {
            startActivity(Intent(this, ListViewActivity::class.java))


            btnGridView.setOnClickListener {
                startActivity(Intent(this, GridViewActivity::class.java))

            }


            btnRecycler.setOnClickListener {
                startActivity(Intent(this, RecyclerViewActivity::class.java))

            }



            btnPageView.setOnClickListener {
                startActivity(Intent(this, ViewPagerActivity::class.java))

            }


        }
    }
}
