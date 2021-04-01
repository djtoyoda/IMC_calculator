package com.djtoyoda.imc_calculator.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import com.djtoyoda.imc_calculator.R
import com.djtoyoda.imc_calculator.main.EXTRA_MESSAGE
import kotlinx.android.synthetic.main.activity_historico.*

class HistoricoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        val message = intent.getStringExtra(EXTRA_MESSAGE)
        tvTabelaHistorico2.text = message
    }
}