package com.example.desafioaula2ioasys.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioaula2ioasys.presentation.adapters.AdapterBook
import com.example.desafioaula2ioasys.databinding.FragmentBookListBinding
import com.example.desafioaula2ioasys.domain.repositories.BooksRepository
import com.example.desafioaula2ioasys.presentation.viewmodel.BookListViewModel
import com.example.desafioaula2ioasys.util.Resource
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookListFragment : Fragment() {


    private var _binding: FragmentBookListBinding? = null
    private val binding: FragmentBookListBinding get() = _binding!!
    private val bookListViewModel: BookListViewModel by lazy {
        getViewModel()
    }

    private lateinit var adapterBook: AdapterBook
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
        observeData()
        configureListeners()
        setupRecyclerView()
        initBooks()
    }

    private fun configureListeners() {
        binding.searchView.textChangeListener = { input ->
        bookListViewModel.searchBooks(input)
        }
    }

    private fun observeData() {
        bookListViewModel.listBooks.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgessBar()
                    hideMessage()
                    response.data?.let { booksResponse ->
                        adapterBook.differ.submitList(booksResponse.data.toList())
                        val totalPages = booksResponse.totalPages
                        isLastPage = bookListViewModel.booksPage == totalPages
                        if (isLastPage) {
                            binding.recyclerBooks.setPadding(0, 0, 0, 0)
                        }
                    }
                }
                is Resource.Loading -> {
                    hideMessage()
                    showProgressBar()
                }
                is Resource.Error -> {
                    showMessage(response.message)
                    response.data?.let{ booksResponse ->
                        adapterBook.differ.submitList(booksResponse.data.toList())
                    }
                    hideProgessBar()
                }
            }
        })


    }

    private fun initBooks() {
        bookListViewModel.getListBooks()
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
                bookListViewModel.getListBooks()
                isScrolling = false
            }
        }
    }

    private fun setupClickAdapter() {
        adapterBook.setOnItemClickListener { book ->
            BookDetailsBottomSheet.newInstance(book).show(childFragmentManager, "book")
        }
    }

    private fun hideProgessBar() {
        binding.progressBar.visibility = View.GONE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        isLoading = true
    }

    private fun hideMessage(){
        binding.textViewEmptyList.visibility = View.GONE
    }

    private fun showMessage(message:String?){
        binding.textViewEmptyList.visibility = View.VISIBLE
        binding.textViewEmptyList.text = message
    }

}