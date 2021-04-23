package com.djtoyoda.imc_calculator.application

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.djtoyoda.imc_calculator.Helpers.HelperDB
import com.djtoyoda.imc_calculator.R

class ImcAdapter(private val listaIMC: MutableList<DataIMC>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        //private val TYPE_HEADER = 0
        private val TYPE_ITEM = 1
    }

    inner class ItemViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val btApagar: ImageView = itemView.findViewById(R.id.btApagar)
        val dateTextView: TextView = itemView.findViewById(R.id.tvDate)
        val pesoTextView: TextView = itemView.findViewById(R.id.tvPeso)
        val imcTextView: TextView = itemView.findViewById(R.id.tvIMC)
    }

    inner class HeaderViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //if (viewType == TYPE_ITEM) {
            val inflater = LayoutInflater.from(parent.context)
            val imcView = inflater.inflate(R.layout.item_view, parent, false)
            return ItemViewHolder(imcView)
        //}
        /*else if (viewType == TYPE_HEADER) {
            val inflater = LayoutInflater.from(parent.context)
            val imcView = inflater.inflate(R.layout.header_view, parent, false)
            return HeaderViewHolder(imcView)
        }
        throw RuntimeException("there is no type that matches the type")*/
    }

    override fun onBindViewHolder(ViewHolder: RecyclerView.ViewHolder, position: Int) {
        if (ViewHolder is ImcAdapter.ItemViewHolder) {
            val imc: DataIMC = listaIMC[position]
            ViewHolder.dateTextView.text = imc.dataDB
            ViewHolder.pesoTextView.text = imc.pesoDB
            ViewHolder.imcTextView.text = imc.imcDB

            val dbHelper = HelperDB(context)
            ViewHolder.btApagar.setOnClickListener() {
                Toast.makeText(context, "Item apagado", Toast.LENGTH_SHORT).show()
                dbHelper.apagarLinhaHistorico(listaIMC[position].idDB)
                listaIMC.removeAt(position)
                notifyDataSetChanged()
            }
        }
        /*else if (ViewHolder is ImcAdapter.HeaderViewHolder) {

        }*/
    }

    override fun getItemCount(): Int = listaIMC.size

    override fun getItemViewType(position: Int): Int {
        return TYPE_ITEM
        //return if (isHeader(position)) TYPE_HEADER else TYPE_ITEM
    }

    private fun isHeader(position: Int): Boolean {
        return position == 0
    }
}