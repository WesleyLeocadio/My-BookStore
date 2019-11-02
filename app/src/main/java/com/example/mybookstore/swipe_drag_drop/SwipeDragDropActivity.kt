package com.example.mybookstore.swipe_drag_drop

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.mybookstore.R
import com.example.mybookstore.connectionDB.AppDatabase
import com.example.mybookstore.domain.Book
import kotlinx.android.synthetic.main.activity_swipe_drag_drop.*


class SwipeDragDropActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_swipe_drag_drop)


        var books:MutableList<Book> = db.bookDao().listAll1()

        var adapter = BookAdapterDragDropView(this, books )
        recyclerview.adapter = adapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerview.layoutManager = layout

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.START or ItemTouchHelper.END  )
        {

            override fun onMove( recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,target: RecyclerView.ViewHolder): Boolean {
                Log.i("AULA17", "OnMove")
                //é usado para operações drag and drop
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                val adapter = recyclerView.adapter as BookAdapterDragDropView
                adapter.mover(fromPosition, toPosition)
                return true// true se moveu, se não moveu, retorne falso
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var posicao = viewHolder.adapterPosition
                var adapter = recyclerview.adapter as BookAdapterDragDropView

                //adapter.remover(posicao)
                adapter.removerComTempo(posicao)

            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val background = ColorDrawable(resources.getColor(R.color.colorPrimaryDark))
                // not sure why, but this method get's called for viewholder that are already swiped away
                if (viewHolder.adapterPosition === -1) {
                    // not interested in those
                    return
                }
                Log.i("AULA17", "dx = $dX")
                // Here, if dX > 0 then swiping right.
                // If dX < 0 then swiping left.
                // If dX == 0 then at at start position.
                // draw red background
                if (dX < 0) {
                    Log.i("AULA17", "dX < 0")
                    background.setBounds(
                        (itemView.right + dX).toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                } else if (dX > 0) {
                    Log.i("AULA17", "dX > 0")
                    background.setBounds(
                        itemView.left,
                        itemView.top,
                        (dX).toInt(),
                        itemView.bottom
                    )
                }
                background.draw(c)

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )

            }

            override fun isLongPressDragEnabled(): Boolean {
                //return false; se quiser, é possivel desabilitar o drag and drop
                return true
            }

            override fun isItemViewSwipeEnabled(): Boolean {
                //return false; se quiser, é possivel desabilitar o swipe
                return true
            }

        })

        itemTouchHelper.attachToRecyclerView(recyclerview)

    }

}



