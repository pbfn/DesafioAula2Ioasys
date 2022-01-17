package com.example.desafioaula2ioasys.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioaula2ioasys.R
import com.example.desafioaula2ioasys.adapters.AdapterBook
import com.example.desafioaula2ioasys.databinding.ActivityHomeBinding
import com.example.desafioaula2ioasys.repository.BookRepository
import com.example.desafioaula2ioasys.util.Resource

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapterBook: AdapterBook
    private lateinit var viewModel: BookViewModel
    private lateinit var token: String
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
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
        viewModel._listBooks.observe(this, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgessBar()
                    adapterBook.differ.submitList(response.data?.data?.toList())
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
        val sharedPref = getSharedPreferences("tokens", Context.MODE_PRIVATE) ?: return
        token = sharedPref.getString("token", "null").toString()
        viewModel.getListBooks("Bearer $token", 1, 20)
    }

    private fun setupRecyclerView() {
        val layout = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        adapterBook = AdapterBook()
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
                isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && !isLoading

            if (shouldPaginate) {
                //viewModel.getListPokemon()
                isScrolling = false
            }
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