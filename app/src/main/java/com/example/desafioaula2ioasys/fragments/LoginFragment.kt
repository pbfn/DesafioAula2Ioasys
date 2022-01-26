package com.example.desafioaula2ioasys.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.desafioaula2ioasys.R
import com.example.desafioaula2ioasys.databinding.FragmentLoginBinding
import com.example.desafioaula2ioasys.repository.LoginRepository
import com.example.desafioaula2ioasys.ui.HomeActivity
import com.example.desafioaula2ioasys.ui.LoginViewModel
import com.example.desafioaula2ioasys.ui.ViewModelProviderLogin
import com.example.desafioaula2ioasys.util.Resource


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    private lateinit var viewModel: LoginViewModel

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
        setupViewModel()
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
        viewModel.doLogin(
            binding.editTextEmail.text.toString(),
            binding.editTextPassword.text.toString()
        )
    }

    private fun setupViewModel() {
        val repository = LoginRepository()
        val viewModelProvider = ViewModelProviderLogin(repository)
        viewModel = ViewModelProvider(this, viewModelProvider).get(LoginViewModel::class.java)
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
        viewModel.responseLogin.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToBookListFragment(response.data)
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
        // binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        //binding.progressBar.visibility = View.VISIBLE
    }

}