package com.example.desafioaula2ioasys.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.desafioaula2ioasys.databinding.FragmentLoginBinding
import com.example.desafioaula2ioasys.presentation.viewmodel.LoginViewModel
import com.example.desafioaula2ioasys.util.Resource
import org.koin.androidx.viewmodel.ext.android.getViewModel


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    private val loginViewModel: LoginViewModel by lazy {
        getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentLoginBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        initComponents()
    }

    private fun initComponents() {
        binding.textViewResponse.visibility = View.GONE
        binding.buttonSent.setOnClickListener(View.OnClickListener {
            if (!checkFields()) {
                binding.textViewResponse.visibility = View.GONE
                doLogin()
            } else {
                binding.textViewResponse.visibility = View.VISIBLE
                binding.textViewResponse.text = "Preencha todos os campos antes de continuar"
            }
        })
    }

    private fun doLogin() {
        loginViewModel.doLogin(
            binding.editTextEmail.text.toString(),
            binding.editTextPassword.text.toString()
        )
    }


    private fun checkFields(): Boolean {
        val email = binding.editTextEmail
        val password = binding.editTextPassword
        var error = false
        if (email.text?.isEmpty() == true || email.text?.isBlank() == true)
            error = true

        if (password.text?.isEmpty() == true || password.text?.isBlank() == true)
            error = true
        return error
    }

    private fun observeData() {
        loginViewModel.responseLogin.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToBookListFragment()
                    )
                }
                is Resource.Loading -> {
                    binding.textViewResponse.visibility = View.GONE
                    showProgressBar()
                }
                is Resource.Error -> {
                    binding.textViewResponse.visibility = View.VISIBLE
                    binding.textViewResponse.text = response.message
                    hideProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
         binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

}