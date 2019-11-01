package com.example.mybookstore.recycler_view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mybookstore.domain.Book
import androidx.recyclerview.widget.RecyclerView
import com.example.mybookstore.R
import com.example.mybookstore.view_holder.BookViewHolder


class BookAdapterRecyclerView (var c:Context, var books:List<Book>) :RecyclerView.Adapter<BookViewHolderRecyclerView>() {

    override fun onBindViewHolder(holder: BookViewHolderRecyclerView, position: Int) {
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

        holder.img.setOnClickListener {
            bookAtual.lido = true
            notifyItemChanged(position)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolderRecyclerView {
        val view = LayoutInflater.from(c).inflate(R.layout.inflater_book_cardview, parent, false)
        return BookViewHolderRecyclerView(view)
    }

    override fun getItemCount(): Int {
        return books.size
    }



}