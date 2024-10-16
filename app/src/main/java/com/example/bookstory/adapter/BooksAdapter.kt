package com.example.bookstory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bookstory.R
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstory.databinding.BookItemBinding

class BooksAdapter(val listener: Listener): RecyclerView.Adapter<BooksAdapter.BooksHolder>(){
    val itemBooksList = ArrayList<ItemBooks>()
    class BooksHolder(item: View): RecyclerView.ViewHolder(item){
        val binding = BookItemBinding.bind(item)
        fun bind(itemBooks: ItemBooks, listener: Listener) = with(binding){
            im.setImageResource(itemBooks.avatarUrl)
            tvNamebook.text = itemBooks.namebook
            tvAuthor.text = itemBooks.author
            itemView.setOnClickListener {
                listener.onClick(itemBooks)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BooksHolder(view)
    }

    override fun getItemCount(): Int {
        return itemBooksList.size
    }

    override fun onBindViewHolder(holder: BooksHolder, position: Int) {
        holder.bind(itemBooksList[position], listener)
    }

    fun addBooks(itemBooks: ItemBooks){
        itemBooksList.add(itemBooks)
        notifyDataSetChanged()
    }
    fun clear() {
        val size: Int = itemBooksList.size
        itemBooksList.clear()
        notifyItemRangeRemoved(0, size)
    }

    interface Listener{
        fun onClick(itemBooks: ItemBooks)
    }
}