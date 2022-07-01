package com.trivi12.pobretito

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.google.firebase.ktx.Firebase
import com.trivi12.pobretito.databinding.ActivityHomeBinding
import com.trivi12.pobretito.viewModels.HomeViewModel

class HomeActivity : AppCompatActivity() {

    private var viewModel: HomeViewModel? = null
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = HomeViewModel(this)

        val bundle = intent.extras
        val email= bundle?.getString("email")
        val password= bundle?.getString("password")

        viewModel!!.saveSession(email.toString(),password.toString())

        binding.btnLogOut.setOnClickListener {
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            viewModel!!.logOut()
        }
    }
}