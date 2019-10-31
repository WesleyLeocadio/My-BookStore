package com.example.mybookstore.view_holder

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

class BookAdapterViewHolder (var context: Context, var books: List<Book>) : BaseAdapter() {

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
        var holder:BookViewHolder
        var view:View
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.inflater_book_listview, parent, false)
            holder = BookViewHolder(view)
            view.tag = holder
        }else{
            view = convertView
            holder = convertView.tag as BookViewHolder
        }



        var bookAtual = books.get(position)
        holder.titulo.text=bookAtual.author
        holder.autor.text = bookAtual.author
       // ano.text=bookAtual.year.toString()
        holder.nota.rating=bookAtual.note
        if (bookAtual.lido) {
           holder.img.setImageResource(R.drawable.livroaberto)
        } else {
            holder.img.setImageResource(R.drawable.livrofechado)
        }




        return view
    }
}