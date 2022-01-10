package com.example.desafioaula2ioasys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.desafioaula2ioasys.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
    }

    private fun initComponents() {
        binding.textViewResponse.visibility = View.GONE
        binding.buttonSent.setOnClickListener(View.OnClickListener {
            if(!checkFields()){
                binding.textViewResponse.visibility = View.GONE
                doLogin()
            }else{
                binding.textViewResponse.visibility = View.VISIBLE
                binding.textViewResponse.text = "Preencha todos os campos antes de continuar"
            }
        })
    }

    private fun doLogin() {
        binding.textViewResponse.visibility = View.VISIBLE
        binding.textViewResponse.text = "Login ok"
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
}
