package com.example.desafioaula2ioasys.ui

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.widget.TextViewCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafioaula2ioasys.R
import com.example.desafioaula2ioasys.adapters.AdapterBook
import com.example.desafioaula2ioasys.databinding.ActivityHomeBinding
import com.example.desafioaula2ioasys.databinding.BottonSheetLayoutBinding
import com.example.desafioaula2ioasys.repository.BookRepository
import com.example.desafioaula2ioasys.util.Resource
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapterBook: AdapterBook
    private lateinit var viewModel: BookViewModel
    private lateinit var token: String
    private lateinit var sharedPref: SharedPreferences
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        sharedPref = getSharedPreferences("tokens", Context.MODE_PRIVATE) ?: return
        token = sharedPref.getString("token", "null").toString()
        setContentView(binding.root)
        setupViewModel()
        observeData()
        setupRecyclerView()
        initBooks()
    }

    private fun setupViewModel() {
        val repository = BookRepository()
        val viewModelProvider = ViewModelProviderBook(repository)
        viewModel = ViewModelProvider(this, viewModelProvider).get(BookViewModel::class.java)
    }

    private fun observeData() {
        viewModel.listBooks.observe(this, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgessBar()
                    response.data?.let { booksResponse ->
                        adapterBook.differ.submitList(booksResponse.data.toList())
                        val totalPages = booksResponse.totalPages
                        isLastPage = viewModel.booksPage == totalPages
                        if (isLastPage) {
                            binding.recyclerBooks.setPadding(0, 0, 0, 0)
                        }
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgessBar()
                }
            }
        })
    }

    private fun initBooks() {
        viewModel.getListBooks("Bearer $token")
    }

    private fun setupRecyclerView() {
        val layout = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        adapterBook = AdapterBook()
        setupClickAdapter()
        binding.recyclerBooks.apply {
            adapter = adapterBook
            layoutManager = layout
            addOnScrollListener(this@HomeActivity.scrollListener)
        }
    }


    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount

            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= 20

            val shouldPaginate =
                isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible

            if (shouldPaginate) {
                viewModel.getListBooks("Bearer $token")
                isScrolling = false
            }
        }
    }

    private fun setupClickAdapter() {
        adapterBook.setOnItemClickListener { book ->
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.botton_sheet_layout, null)
            view.apply {
                val btnClose = findViewById<ImageButton>(R.id.button_close)
                val textViewTitleBook =findViewById<TextView>(R.id.text_view_title)
                val imageBook = findViewById<ImageView>(R.id.image_view_book)
                Glide.with(this).load(book.imageUrl).into(imageBook)
                textViewTitleBook.text = book.title
                btnClose.setOnClickListener {
                    dialog.dismiss()
                }
            }
            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }
    }

    private fun hideProgessBar() {
        binding.progressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        isLoading = true
    }
}