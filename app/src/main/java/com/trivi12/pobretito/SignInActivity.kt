package com.trivi12.pobretito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.Observer
import com.trivi12.pobretito.databinding.ActivitySignInBinding
import com.trivi12.pobretito.viewModels.SignInViewModel

class SignInActivity : AppCompatActivity() {

    private var viewModel : SignInViewModel? = null
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = SignInViewModel(this)

        binding.btnSignIn.setOnClickListener {

            viewModel!!.validateUserData(
                binding.dniEditText.text.toString(),
                binding.nameEditText.text.toString(),
                binding.surnameEditText.text.toString(),
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString())
        }
        setupViewModelObserver()
        setupEditText()
    }

    private fun setupViewModelObserver(){

        viewModel?.dataValidationMutable?.observe(this) { datavalidation ->
            datavalidation?.let {

                if(!datavalidation.dniError.isNullOrEmpty()){
                    binding.dniTextField.error = datavalidation.dniError
                }
                if (!datavalidation.nameError.isNullOrEmpty()) {
                    binding.nameTextField.error = datavalidation.nameError
                }
                if(!datavalidation.surnameError.isNullOrEmpty()){
                    binding.surnameTextField.error = datavalidation.surnameError
                }
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
        binding.dniEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.dniTextField.error = null
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Nothing use
            }
            override fun afterTextChanged(p0: Editable?) {
                // Nothing use
            }
        })

        binding.nameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.nameTextField.error = null
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Nothing use
            }
            override fun afterTextChanged(p0: Editable?) {
                // Nothing use
            }
        })

        binding.surnameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.surnameTextField.error = null
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Nothing use
            }
            override fun afterTextChanged(p0: Editable?) {
                // Nothing use
            }
        })

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
