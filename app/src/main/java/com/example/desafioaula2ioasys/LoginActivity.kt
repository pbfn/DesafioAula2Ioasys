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

    private var _binding: ActivityLoginBinding? = null
    private val binding: ActivityLoginBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
