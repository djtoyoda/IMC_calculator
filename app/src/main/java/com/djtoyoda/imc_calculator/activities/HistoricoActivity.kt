package com.djtoyoda.imc_calculator.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.djtoyoda.imc_calculator.R
import com.djtoyoda.imc_calculator.application.DataIMC
import com.djtoyoda.imc_calculator.application.ImcAdapter
import com.djtoyoda.imc_calculator.main.EXTRA_MESSAGE

class HistoricoActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view)

        val rvIMC = findViewById<View>(R.id.recyclerViewIMC) as RecyclerView
        val imc = intent.getSerializableExtra(EXTRA_MESSAGE) as MutableList<DataIMC>
        val adapter = ImcAdapter(imc, applicationContext)
        rvIMC.adapter = adapter
        rvIMC.layoutManager = LinearLayoutManager(this)
    }
}