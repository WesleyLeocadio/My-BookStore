package com.example.mybookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybookstore.auto_complete_textview.AutoCompleteTextViewActivity
import com.example.mybookstore.register.RegisterActivity
import com.example.mybookstore.search_books.SearchBooksActivity
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




    }
}
