package com.example.desafioaula2ioasys

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.desafioaula2ioasys.databinding.ActivityLoginBinding
import com.example.desafioaula2ioasys.repository.LoginRepository
import com.example.desafioaula2ioasys.ui.HomeActivity
import com.example.desafioaula2ioasys.ui.LoginViewModel
import com.example.desafioaula2ioasys.ui.ViewModelProviderLogin
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
        if (email.text?.isEmpty() == true  || email.text?.isBlank() == true)
            error = true

        if (password.text?.isEmpty() == true || password.text?.isBlank() == true)
            error = true
        return error
    }

    private fun setupViewModel() {
        val repository = LoginRepository()
        val viewModelProvider = ViewModelProviderLogin(repository)
        viewModel = ViewModelProvider(this, viewModelProvider).get(LoginViewModel::class.java)
    }

    private fun observeData() {
        viewModel.responseLogin.observe(this, { response ->
            when (response) {
                is Resource.Success -> {
                    val intent = Intent(this,HomeActivity::class.java)
                    val sharedPref = getSharedPreferences("tokens", Context.MODE_PRIVATE)
                    with(sharedPref.edit()){
                        putString("token",response.data)
                        commit()
                    }
                    intent.putExtra("token",response.data)
                    startActivity(intent)
                    finish()
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
