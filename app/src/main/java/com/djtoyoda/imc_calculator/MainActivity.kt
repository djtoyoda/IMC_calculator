package com.djtoyoda.imc_calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }
    private fun setListeners() {
        btCalcular?.setOnClickListener {
            calcularIMC(etPeso.text.toString(), etAltura.text.toString())
        }
    }

    private fun calcularIMC(peso: String, altura: String) {
        var peso = etPeso.toFloatOrNull()
        var altura= etAltura.toFloatOrNull()
        var imc = peso / altura / altura
        //tvResultado.text = "Seu IMC é $imc"

    }

}