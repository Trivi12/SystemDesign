package com.trivi12.pobretito

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.trivi12.pobretito.databinding.ActivityLogInBinding
import com.trivi12.pobretito.viewModels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private var viewModel: LoginViewModel? = null
    private  lateinit var binding : ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = LoginViewModel(this)

        binding.btnLogIn.setOnClickListener {

            viewModel!!.validateUserData(
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString())

        }
        setupViewModelObserver()
        setupEditText()

        binding.btnSignIn.setOnClickListener {
            viewModel!!.goSignIn()
        }

    }

    override fun onStart() {
        super.onStart()
        binding.loginLayout.visibility = View.VISIBLE
    }

    private fun setupViewModelObserver(){

        viewModel?.dataValidationMutable?.observe(this) { datavalidation ->
            datavalidation?.let {

                if(!datavalidation.emailError.isNullOrEmpty()){
                    binding.emailTextField.error = datavalidation.emailError
                }
                if(!datavalidation.passwordError.isNullOrEmpty()){
                    binding.passwordTextField.error = datavalidation.passwordError
                }
            }
        }
    }

    private fun setupEditText() {
        binding.emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.emailTextField.error = null
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Nothing use
            }

            override fun afterTextChanged(p0: Editable?) {
                // Nothing use
            }
        })

        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.passwordTextField.error = null
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Nothing use
            }

            override fun afterTextChanged(p0: Editable?) {
                // Nothing use
            }
        })
    }
}