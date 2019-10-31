package com.example.mybookstore.list_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.mybookstore.R
import com.example.mybookstore.domain.Book

class BookAdapter (var context: Context, var books: List<Book>) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        return books.get(position)
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getCount(): Int {
        return books.size
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = LayoutInflater.from(context).inflate(R.layout.inflater_book_listview, parent, false)
        var title: TextView = v.findViewById(R.id.textInfTitle)
        var ator: TextView = v.findViewById(R.id.textInfAutor)
       // var ano: TextView = v.findViewById(R.id.textInfNota)
        val nota: RatingBar = v.findViewById(R.id.textInfNota)

        val img: ImageView=v.findViewById(R.id.img)
        var bookAtual = books.get(position)
        title.text=bookAtual.author
        ator.text = bookAtual.author
       // ano.text=bookAtual.year.toString()
        nota.rating=bookAtual.note
        if (bookAtual.lido) {
            img.setImageResource(R.drawable.livroaberto)
        } else {
            img.setImageResource(R.drawable.livrofechado)
        }




        return v
    }
}