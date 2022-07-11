package com.trivi12.pobretito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.DocumentSnapshot
import com.trivi12.pobretito.databinding.ActivityHistoryBinding
import com.trivi12.pobretito.models.Incident
import com.trivi12.pobretito.viewModels.HistoryViewModel

class HistoryActivity : AppCompatActivity() {

    private var viewModel: HistoryViewModel? = null
    private lateinit var binding : ActivityHistoryBinding

    private lateinit var incidentsList: ArrayList<Incident>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = HistoryViewModel(this,getSharedPreferences(getString(R.string.prefs_file),
            MODE_PRIVATE))

        title = "Historial de reclamos"

        val bundle = intent.extras
        val email = bundle?.getString("email")

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        viewModel!!.listIncidents.observe(this){ list ->
            incidentsList = list as ArrayList<Incident>
            setupList()
        }

        viewModel!!.getIncidents(email!!)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) { onBackPressed() }
        return super.onOptionsItemSelected(item)
    }

    private fun setupList(){
        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        val adapter = HistoryAdapter(incidentsList)

        binding.rvHistory.adapter = adapter
    }


}