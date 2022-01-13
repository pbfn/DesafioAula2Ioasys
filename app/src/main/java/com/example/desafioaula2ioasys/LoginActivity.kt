package com.example.desafioaula2ioasys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.desafioaula2ioasys.databinding.ActivityLoginBinding
import com.example.desafioaula2ioasys.repository.BooksRepository
import com.example.desafioaula2ioasys.ui.LoginViewModel
import com.example.desafioaula2ioasys.ui.ViewModelProviderBook
import com.example.desafioaula2ioasys.util.Resource

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

    private fun checkFields(): Boolean {
        val email = binding.editTextEmail
        val password = binding.editTextPassword
        var error = false
        if (email.text.isEmpty() || email.text.isBlank())
            error = true

        if (password.text.isEmpty() || password.text.isBlank())
            error = true
        return error
    }

    private fun setupViewModel() {
        val repository = BooksRepository()
        val viewModelProvider = ViewModelProviderBook(repository)
        viewModel = ViewModelProvider(this, viewModelProvider).get(LoginViewModel::class.java)
    }

    private fun observeData() {
        viewModel.responseLogin.observe(this, { response ->
            when (response) {
                is Resource.Success -> {
                    binding.textViewResponse.visibility = View.VISIBLE
                    binding.textViewResponse.text = "Login ok"
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
