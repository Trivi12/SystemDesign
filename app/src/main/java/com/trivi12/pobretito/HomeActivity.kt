package com.trivi12.pobretito

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.ktx.Firebase
import com.trivi12.pobretito.databinding.ActivityHomeBinding
import com.trivi12.pobretito.viewModels.HomeViewModel

class HomeActivity : AppCompatActivity() {

    private var viewModel: HomeViewModel? = null
    private lateinit var binding : ActivityHomeBinding
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = HomeViewModel(this)

        val bundle = intent.extras
        val email= bundle?.getString("email")
        val password= bundle?.getString("password")
        println("homeEMAIL..${email.toString()}")
        println("homePASS..${password.toString()}")

        viewModel!!.saveSession(email.toString(),password.toString())

        // Funcion para la barra de navegacion inferior
        /*binding!!.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.btnHistory ->{}*/

        binding.btnAdd.setOnClickListener {
            viewModel!!.goIncident(email.toString())
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        this.menu = menu

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.logOut ->{
                viewModel!!.showAlert()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}