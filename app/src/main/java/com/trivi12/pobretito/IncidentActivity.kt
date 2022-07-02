package com.trivi12.pobretito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trivi12.pobretito.databinding.ActivityHomeBinding
import com.trivi12.pobretito.databinding.ActivityIncidentBinding
import com.trivi12.pobretito.viewModels.HomeViewModel
import com.trivi12.pobretito.viewModels.IncidetViewModel

class IncidentActivity : AppCompatActivity() {

    private var viewModel:IncidetViewModel? = null
    private lateinit var binding: ActivityIncidentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "Incidente"

        binding = ActivityIncidentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = IncidetViewModel(this)
    }
}