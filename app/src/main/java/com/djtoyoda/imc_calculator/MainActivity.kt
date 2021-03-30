package com.djtoyoda.imc_calculator

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    fun setListeners() {
        btCalcular.setOnClickListener {
            calcularIMC(etPeso.text.toString(), etAltura.text.toString())
        }

        etAltura.doOnTextChanged {text, start, count, after ->
            tvResultado.text = ""
        }

        etPeso.doOnTextChanged {text, start, count, after ->
            tvResultado.text = ""
        }
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
                in 0.0 .. 16.9 -> {Faixa1.setTextColor(Color.parseColor("#FF5200")); Sign1.setTextColor(Color.parseColor("#FF5200"))}
                in 17.0 .. 18.4 -> {Faixa2.setTextColor(Color.parseColor("#FFA300")); Sign2.setTextColor(Color.parseColor("#FFA300"))}
                in 18.5 .. 24.9 -> {Faixa3.setTextColor(Color.parseColor("#45FF00")); Sign3.setTextColor(Color.parseColor("#45FF00"))}
                in 25.0 .. 29.9 -> {Faixa4.setTextColor(Color.parseColor("#FFA300")); Sign4.setTextColor(Color.parseColor("#FFA300"))}
                in 30.0 .. 34.9 -> {Faixa5.setTextColor(Color.parseColor("#FF000D")); Sign5.setTextColor(Color.parseColor("#FF000D"))}
                in 35.0 .. 39.9 -> {Faixa6.setTextColor(Color.parseColor("#FF000D")); Sign6.setTextColor(Color.parseColor("#FF000D"))}
                else -> {Faixa7.setTextColor(Color.parseColor("#FF000D")); Sign7.setTextColor(Color.parseColor("#FF000D"))}
            }
        }
    }
}