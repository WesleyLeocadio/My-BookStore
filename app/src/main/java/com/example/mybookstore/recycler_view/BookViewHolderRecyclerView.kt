package com.example.mybookstore.recycler_view

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybookstore.R

class BookViewHolderRecyclerView  (v: View) : RecyclerView.ViewHolder(v){

    val titulo: TextView
    val autor: TextView
    val nota: RatingBar
    val img: ImageView

    init {
        Log.i("HOLDER", "Fazendo buscas por id...")
        titulo = v.findViewById(R.id.textInfTitle)
        autor = v.findViewById(R.id.textInfAutor)
        nota = v.findViewById(R.id.textInfNota)
        img = v.findViewById(R.id.img)

    }
}