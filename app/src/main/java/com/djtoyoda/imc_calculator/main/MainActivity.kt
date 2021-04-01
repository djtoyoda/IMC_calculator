package com.djtoyoda.imc_calculator.main

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.djtoyoda.imc_calculator.Helpers.HelperDB
import com.djtoyoda.imc_calculator.R
import com.djtoyoda.imc_calculator.application.DataIMC
import kotlinx.android.synthetic.main.activity_historico.*
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setListeners() {
        btCalcular.setOnClickListener {
            calcularIMC(etPeso.text.toString(), etAltura.text.toString())
        }

        btLimpar.setOnClickListener {
            etAltura.setText("")
            etPeso.setText("")
        }

        btGuardar.setOnClickListener {
            val date = LocalDate.now()
            val parsedDate = date.format(DateTimeFormatter.ISO_DATE).toString()
            val imcStr = returnIMC(etPeso.text.toString(), etAltura.text.toString())
            val db = HelperDB(this)
            val newIMCData = DataIMC(parsedDate, etPeso.text.toString(), imcStr)
            db.adicionarIMC(newIMCData)
        }

        const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

        btHistorico.setOnClickListener {
            val db = HelperDB(this)
            val data = db.lerHistorico()


            val intent = Intent(this, activity_historico::class.java).apply {
                startActivity(intent)


                for (i in 0 until data.size) {
                   tvTabelaHistorico.append(
                           data[i].dataID.toString() + " " + data[i].pesoDB + " " + data[i].imcDB + "\n")
                }

                   putExtra(EXTRA_MESSAGE, message)
               }
        }

        etAltura.doOnTextChanged { _, _, _, _ ->
            tvResultado.text = ""
        }

        etPeso.doOnTextChanged { _, _, _, _ ->
            tvResultado.text = ""
        }
    }
    fun returnIMC(peso: String, altura: String): String {
        if (peso.isNullOrEmpty() || altura.isNullOrEmpty()) {
            tvResultado.text = "Favor inserir peso e altura"
        }
        else {
            val imc = peso.toDouble() / altura.toDouble() / altura.toDouble()
            return String.format("%.1f", imc)
        }
        var x = "erro"
        return x
    }
    fun calcularIMC(peso: String, altura: String) {
        if (peso.isNullOrEmpty() || altura.isNullOrEmpty()) {
            tvResultado.text = "Favor inserir peso e altura"
        }
        else {
            val imc = peso.toDouble() / altura.toDouble() / altura.toDouble()
            val roundIMC = String.format("%.1f", imc)
            var doubleIMC = roundIMC.toDouble()
            tvResultado.text = "Seu IMC é ${String.format("%.1f", imc)}"

            //reset color
            for (i in 1..7) {
                val nomeStringFaixa = "Faixa" + i
                val nomeStringSign = "Sign" + i
                val id1 = resources.getIdentifier(nomeStringFaixa, "id", packageName)
                val id2 = resources.getIdentifier(nomeStringSign, "id", packageName)
                val textReset1 = findViewById<View>(id1) as TextView
                val textReset2 = findViewById<View>(id2) as TextView
                textReset1.setTextColor(Color.parseColor("#000000"))
                textReset2.setTextColor(Color.parseColor("#000000"))
            }

            //colore faixa do usuario
            when (doubleIMC){
                in 0.0..16.9 -> {
                    Faixa1.setTextColor(Color.parseColor("#FF5200")); Sign1.setTextColor(Color.parseColor("#FF5200"))
                }
                in 17.0..18.4 -> {
                    Faixa2.setTextColor(Color.parseColor("#FFA300")); Sign2.setTextColor(Color.parseColor("#FFA300"))
                }
                in 18.5..24.9 -> {
                    Faixa3.setTextColor(Color.parseColor("#45FF00")); Sign3.setTextColor(Color.parseColor("#45FF00"))
                }
                in 25.0..29.9 -> {
                    Faixa4.setTextColor(Color.parseColor("#FFA300")); Sign4.setTextColor(Color.parseColor("#FFA300"))
                }
                in 30.0..34.9 -> {
                    Faixa5.setTextColor(Color.parseColor("#FF000D")); Sign5.setTextColor(Color.parseColor("#FF000D"))
                }
                in 35.0..39.9 -> {
                    Faixa6.setTextColor(Color.parseColor("#FF000D")); Sign6.setTextColor(Color.parseColor("#FF000D"))
                }
                else -> {Faixa7.setTextColor(Color.parseColor("#FF000D")); Sign7.setTextColor(Color.parseColor("#FF000D"))}
            }
        }
    }
}