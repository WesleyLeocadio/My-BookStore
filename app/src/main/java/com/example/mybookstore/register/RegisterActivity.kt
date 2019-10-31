package com.example.mybookstore.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.mybookstore.R
import com.example.mybookstore.connectionDB.AppDatabase
import com.example.mybookstore.domain.Book
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_register)

        btnSave.setOnClickListener {
            try {
                if (!textAuthor.text.equals("") && !textAuthor.text.equals("")) {
                    db.bookDao().insert(
                        Book(
                            textName.text.toString(),
                            textAuthor.text.toString(),
                            textYear.text.toString().toInt(),
                            ratingBar.rating,
                            R.drawable.livrofechado,
                            false
                        )
                    )
                    clearFields()
                    Toast.makeText(this, "Registered!", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    Toast.makeText(this, "Title and actor fields are required", Toast.LENGTH_SHORT)
                        .show()


                }

            } catch (e: java.lang.NumberFormatException) {

                Toast.makeText(this, "You may have entered mismatched data in the year or grade fields.", Toast.LENGTH_SHORT)
                    .show()
            }



        }

        btnCancel.setOnClickListener {
            finish()
        }
    }



    fun clearFields() {
        textName.setText("")
        textAuthor.setText("")
        textYear.setText("")
        ratingBar.rating = 0f
    }

}
