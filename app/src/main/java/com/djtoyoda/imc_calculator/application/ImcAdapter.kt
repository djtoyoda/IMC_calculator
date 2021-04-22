package com.djtoyoda.imc_calculator.application

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.djtoyoda.imc_calculator.Helpers.HelperDB
import com.djtoyoda.imc_calculator.R

class ImcAdapter (private val listaIMC: MutableList<DataIMC>, private val context: Context) : RecyclerView.Adapter<ImcAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val btApagar: ImageView = itemView.findViewById(R.id.btApagar)
        val dateTextView: TextView = itemView.findViewById(R.id.tvDate)
        val pesoTextView: TextView = itemView.findViewById(R.id.tvPeso)
        val imcTextView: TextView = itemView.findViewById(R.id.tvIMC)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImcAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val imcView = inflater.inflate(R.layout.item_view, parent,false)
        return ViewHolder(imcView)
    }

    override fun onBindViewHolder(viewHolder: ImcAdapter.ViewHolder, position: Int) {
        val imc: DataIMC = listaIMC[position]
        viewHolder.dateTextView.text = imc.dataDB
        viewHolder.pesoTextView.text = imc.pesoDB
        viewHolder.imcTextView.text = imc.imcDB

        var dbHelper = HelperDB(context)
        viewHolder.btApagar.setOnClickListener() {
            //Toast.makeText(context, "Apagar", Toast.LENGTH_SHORT).show()
            dbHelper.apagarLinhaHistorico(listaIMC[position].idDB)
            listaIMC.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = listaIMC.size
}