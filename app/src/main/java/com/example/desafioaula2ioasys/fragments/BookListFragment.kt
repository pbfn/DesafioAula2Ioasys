package com.example.desafioaula2ioasys.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafioaula2ioasys.R
import com.example.desafioaula2ioasys.adapters.AdapterBook
import com.example.desafioaula2ioasys.databinding.ActivityHomeBinding
import com.example.desafioaula2ioasys.databinding.FragmentBookListBinding
import com.example.desafioaula2ioasys.repository.BookRepository
import com.example.desafioaula2ioasys.ui.BookViewModel
import com.example.desafioaula2ioasys.ui.ViewModelProviderBook
import com.example.desafioaula2ioasys.util.Resource
import com.google.android.material.bottomsheet.BottomSheetDialog

class BookListFragment : Fragment() {


    private var _binding: FragmentBookListBinding? = null
    private val binding: FragmentBookListBinding get() = _binding!!
    private val args: BookListFragmentArgs by navArgs()

    private lateinit var adapterBook: AdapterBook
    private lateinit var viewModel: BookViewModel
    private lateinit var token: String
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentBookListBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        token = args.token.toString()
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
        viewModel.listBooks.observe(viewLifecycleOwner, { response ->
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
        val layout = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapterBook = AdapterBook()
        setupClickAdapter()
        binding.recyclerBooks.apply {
            adapter = adapterBook
            layoutManager = layout
            addOnScrollListener(scrollListener)
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
            val dialog = BottomSheetDialog(requireContext())
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