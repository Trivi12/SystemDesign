package com.trivi12.pobretito

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextClock
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.trivi12.pobretito.databinding.HistoryCardBinding
import com.trivi12.pobretito.models.Incident

class HistoryAdapter(
    private val listIncident: ArrayList<Incident>
): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = HistoryCardBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return HistoryViewHolder(binding, viewGroup.context)
    }

    override fun onBindViewHolder(viewHolder: HistoryViewHolder, position: Int) {
        viewHolder.bind(listIncident[position])
    }

    override fun getItemCount() = listIncident.size

    inner class HistoryViewHolder(
        private  val itemView:HistoryCardBinding,
        private val context: Context):RecyclerView.ViewHolder(itemView.root) {

        fun bind(incidents: Incident){

            val chip = itemView.findViewById<Chip>(R.id.chipDate)
            chip.text = incidents.date

            val tvNumIncident = itemView.findViewById<TextView>(R.id.tvNumIncident)
            tvNumIncident.text = "Reclamo N°: ${incidents.num}"

            itemView.findViewById<LinearLayout>(R.id.cardAddress).apply {
                this.findViewById<TextView>(R.id.tvIncidentTitle).text = "Direccion: "
                this.findViewById<TextView>(R.id.tvIncidentData).text = incidents.address
            }

            itemView.findViewById<LinearLayout>(R.id.cardCategory).apply {
                this.findViewById<TextView>(R.id.tvIncidentTitle).text = "Categoria: "
                this.findViewById<TextView>(R.id.tvIncidentData).text = incidents.category
            }

            itemView.findViewById<LinearLayout>(R.id.cardDescription).apply {
                this.findViewById<TextView>(R.id.tvIncidentTitle).text = "Descripcion: "
                this.findViewById<TextView>(R.id.tvIncidentData).text = incidents.description
            }

            itemView.findViewById<LinearLayout>(R.id.cardCondition).apply {
                this.findViewById<TextView>(R.id.tvIncidentTitle).text = "Condicion: "
                this.findViewById<TextView>(R.id.tvIncidentData).text = incidents.condition
            }


            //TODO: setear resultados en la card


        }
    }
}