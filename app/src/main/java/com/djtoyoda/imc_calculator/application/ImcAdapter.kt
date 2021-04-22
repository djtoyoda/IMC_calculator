package com.djtoyoda.imc_calculator.application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.djtoyoda.imc_calculator.R
import kotlinx.android.synthetic.main.item_view.view.*

class ImcAdapter (private val listaIMC: List<DataIMC>) : RecyclerView.Adapter<ImcAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val dateTextView: TextView = itemView.findViewById<TextView>(R.id.tvDate)
        val pesoTextView: TextView = itemView.findViewById<TextView>(R.id.tvPeso)
        val imcTextView: TextView = itemView.findViewById<TextView>(R.id.tvIMC)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImcAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val imcView = inflater.inflate(R.layout.item_view, parent,false)
        return ViewHolder(imcView)
    }

    override fun onBindViewHolder(viewHolder: ImcAdapter.ViewHolder, position: Int) {
        val imc: DataIMC = listaIMC.get(position)
        viewHolder.dateTextView.text = imc.dataID
        viewHolder.pesoTextView.text = imc.pesoDB
        viewHolder.imcTextView.text = imc.imcDB
    }

    override fun getItemCount(): Int = listaIMC.size
}