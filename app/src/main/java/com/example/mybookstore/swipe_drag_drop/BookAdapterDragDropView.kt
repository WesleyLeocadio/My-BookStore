package com.example.mybookstore.swipe_drag_drop

import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mybookstore.domain.Book
import androidx.recyclerview.widget.RecyclerView
import com.example.mybookstore.R
import com.example.mybookstore.view_holder.BookViewHolder
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class BookAdapterDragDropView(var c: Context, var books: MutableList<Book>) :
    RecyclerView.Adapter<BookViewHolderDragDropView>() {


    private val PENDING_REMOVAL_TIMEOUT: Long = 3000 // 3sec
    var itemsPendingRemoval = ArrayList<Book>()


    private val handler = Handler() // hanlder que vai guardar os runnables que devem ser executados
    var pendingRunnables: HashMap<Book, Runnable> =
        HashMap() // map de livros com runnables pendentes, para que seja possível cancelar

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolderDragDropView {
        val view =
            LayoutInflater.from(c).inflate(R.layout.new_book_infater_dragdrop, parent, false)
        return BookViewHolderDragDropView(view)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookViewHolderDragDropView, position: Int) {
        var bookAtual = books.get(position)

        holder.titulo.text = bookAtual.author
        holder.autor.text = bookAtual.author
        // ano.text=bookAtual.year.toString()
        holder.nota.rating = bookAtual.note
        if (bookAtual.lido) {
            holder.img.setImageResource(R.drawable.livroaberto)
        } else {
            holder.img.setImageResource(R.drawable.livrofechado)
        }

        if (itemsPendingRemoval.contains(bookAtual)) {
            //view do swipe/delete
            holder.layoutNormal.setVisibility(View.GONE)
            holder.layoutGone.setVisibility(View.VISIBLE)
            holder.undoButton.setVisibility(View.VISIBLE)
            holder.undoButton.setOnClickListener {
                // usou o undo, remover a pendingRennable
                val pendingRemovalRunnable = pendingRunnables[bookAtual]
                Log.i("AULA17", "CLICOU")
                pendingRunnables.remove(bookAtual)
                if (pendingRemovalRunnable != null) {
                    handler.removeCallbacks(pendingRemovalRunnable)
                }
                itemsPendingRemoval.remove(bookAtual)
                //binda novamente para redesenhar
                notifyItemChanged(books.indexOf(bookAtual))
            }
        } else {
            //mostra o padrão
            holder.titulo.text = bookAtual.author
            holder.autor.text = bookAtual.author
            // ano.text=bookAtual.year.toString()
            holder.nota.rating = bookAtual.note
            if (bookAtual.lido) {
                holder.img.setImageResource(R.drawable.livroaberto)
            } else {
                holder.img.setImageResource(R.drawable.livrofechado)
            }
            holder.layoutNormal.setVisibility(View.VISIBLE)
            holder.layoutGone.setVisibility(View.GONE)
            holder.undoButton.setVisibility(View.GONE)
            holder.undoButton.setOnClickListener(null)


            holder.img.setOnClickListener {
                bookAtual.lido = true
                notifyItemChanged(position)
            }


        }
    }

    fun remover(position: Int) {
        var book = books[position]

        if (books.contains(book)) {
            books.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun removerComTempo(position: Int) {

        val book = books[position]
        if (!itemsPendingRemoval.contains(book)) {
            itemsPendingRemoval.add(book)
            notifyItemChanged(position)
            var pendingRemovalRunnable = Runnable {
                remover(position)
            }
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT)
            pendingRunnables[book] = pendingRemovalRunnable
        }
    }

    fun mover(fromPosition: Int, toPosition: Int) {

        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(books, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(books, i, i - 1)
            }
        }

        notifyItemMoved(fromPosition, toPosition)
        notifyItemChanged(toPosition)
        notifyItemChanged(fromPosition)
    }


}
