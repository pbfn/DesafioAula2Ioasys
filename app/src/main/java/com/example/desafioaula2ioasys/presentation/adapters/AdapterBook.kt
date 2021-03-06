package com.example.desafioaula2ioasys.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafioaula2ioasys.databinding.BookItemAdapterBinding
import com.example.desafioaula2ioasys.domain.model.Book

class AdapterBook() : RecyclerView.Adapter<AdapterBook.AdpterBooksViewHolder>() {


    class AdpterBooksViewHolder(itemView: BookItemAdapterBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val image = itemView.imageViewBook
        val title = itemView.textViewTitle
        val author = itemView.textViewAuthor
        val description = itemView.textViewDescription
        val card = itemView.cardBook
    }

    private val differCallback = object : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdpterBooksViewHolder {
        val binding = BookItemAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AdpterBooksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdpterBooksViewHolder, position: Int) {
        val book = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(book.imageUrl).into(holder.image)
        }
        holder.apply {
            author.text = book.authors?.get(0)
            title.text = book.title
            description.text = book.description
            card.setOnClickListener {
                onItemClickListener?.let {
                    it(differ.currentList[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
      return differ.currentList.size
    }

    private var onItemClickListener: ((Book) -> Unit)? = null

    fun setOnItemClickListener(listener: (Book) -> Unit) {
        onItemClickListener = listener
    }
}