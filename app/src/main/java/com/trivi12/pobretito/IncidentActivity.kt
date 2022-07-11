package com.trivi12.pobretito

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.trivi12.pobretito.databinding.ActivityIncidentBinding
import com.trivi12.pobretito.viewModels.IncidentViewModel


class IncidentActivity : AppCompatActivity() {

    private var viewModel: IncidentViewModel? = null
    private lateinit var binding: ActivityIncidentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "Generar reclamo"

        binding = ActivityIncidentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = IncidentViewModel(this)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }


        val bundle = intent.extras
        val email = bundle?.getString("email")

        setUpView()

        binding.btnGenerate.setOnClickListener {

            viewModel!!.validateIncidentData(binding.categoryEditText.text.toString(),
                binding.descriptionEditText.text.toString(), binding.addressEditText.text.toString(),email.toString())
        }
        setupViewModelObserver()
        setupEditText()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) { onBackPressed() }
        return super.onOptionsItemSelected(item)
    }

    fun setUpView(){

        val items = listOf("Alumbrado publico", "Bacheo", "Cloacas",
            "Espacios verdes", "Predios baldios", "Semaforos")
        val adapter = ArrayAdapter(this, R.layout.item_category, items)
        (binding.categoryEditText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun setupViewModelObserver(){

        viewModel?.dataValidationMutable?.observe(this) { datavalidation ->
            datavalidation?.let {

                if(!datavalidation.categoryError.isNullOrEmpty()){
                    binding.categoryTextField.error = datavalidation.categoryError
                }
                if(!datavalidation.descriptionError.isNullOrEmpty()){
                    binding.descriptionTextField.error = datavalidation.descriptionError
                }
                if(!datavalidation.addressError.isNullOrEmpty()){
                    binding.addressTextField.error = datavalidation.addressError
                }
            }
        }
    }

    private fun setupEditText() {
        binding.categoryEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.categoryTextField.error = null
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Nothing use
            }

            override fun afterTextChanged(p0: Editable?) {
                // Nothing use
            }
        })

        binding.descriptionEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.descriptionTextField.error = null
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Nothing use
            }

            override fun afterTextChanged(p0: Editable?) {
                // Nothing use
            }
        })

        binding.addressEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.addressTextField.error = null
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