package com.example.mybookstore.view_pager

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.widget.ImageView
import com.example.mybookstore.R
import com.example.mybookstore.domain.Book


class PageViewAdapter(var context:Context, var livros:List<Book>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        Log.i("TESTE","INSTANCIOU ")

        val view = LayoutInflater.from(context)
            .inflate(R.layout.page_view, container, false)

        val img:ImageView = view.findViewById(R.id.imgPage)
        if (livros[position].lido) {
            img.setImageResource(R.drawable.livroaberto)
        } else {
            img.setImageResource(R.drawable.livrofechado)
        }

       // img.setImageResource(livros[position].img)
       // Log.i("imagem","${livros[position].img}")
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return livros.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        Log.i("TESTE","${ livros[position].name}")
        return livros[position].name
    }
}