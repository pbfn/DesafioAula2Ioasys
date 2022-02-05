package com.example.desafioaula2ioasys.presentation.ui.fragments

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ImageSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.desafioaula2ioasys.R
import com.example.desafioaula2ioasys.databinding.BottomSheetBookDetailsBinding
import com.example.desafioaula2ioasys.domain.model.Book
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BookDetailsBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetBookDetailsBinding? = null
    private val binding: BottomSheetBookDetailsBinding get() = _binding!!

    private var book: Book? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = BottomSheetBookDetailsBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setListeners()
        setupBootomSheetHeight()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners(){
        binding.buttonClose.setOnClickListener{
            dismiss()
        }
    }

    private fun setupView(){
        val sppnableString = SpannableString("   ${book?.description}")
        val imageSpan = ImageSpan(requireContext(),R.drawable.ic_quotes)
        sppnableString.setSpan(imageSpan,0,1,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)

        binding.apply {
            Glide.with(requireActivity()).load(book?.imageUrl).into(imageViewBook)
            textViewTitle.text = book?.title
            textViewAuthors.text = book?.authors?.get(0)
            textPages.text = book?.pageCount.toString()
            textPublishing.text = book?.publisher
            textPublication.text = book?.published.toString()
            textLanguage.text = book?.language
            textOriginalTitle.text = book?.title
            textIsbn10.text = book?.isbn10
            textIsbn13.text = book?.isbn13
            textReview.text = sppnableString
        }

    }

    private fun setupBootomSheetHeight(){
        (dialog as BottomSheetDialog) .behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }

    companion object {
        fun newInstance(book: Book? = null): BookDetailsBottomSheet {
            return BookDetailsBottomSheet().apply {
                this.book = book
            }
        }
    }

}