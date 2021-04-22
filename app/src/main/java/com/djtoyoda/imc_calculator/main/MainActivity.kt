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
import com.djtoyoda.imc_calculator.activities.HistoricoActivity
import com.djtoyoda.imc_calculator.application.DataIMC
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

const val EXTRA_MESSAGE = "com.djtoyoda.imc.MESSAGE"

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setListeners() {

        //botao para calcular IMC e mostrar resultado
        btCalcular.setOnClickListener {
            mostrarIMC(returnIMC(etPeso.text.toString(), etAltura.text.toString()))
        }

        //botao para limpar campos
        btLimpar.setOnClickListener {
            etAltura.setText("")
            etPeso.setText("")
        }

        //botao para salvar IMC no historico
        btGuardar.setOnClickListener {
            guardarHistorico(etPeso.text.toString(),etAltura.text.toString())
        }

        //botao para mostrar historico na activity_historico
        btHistorico.setOnClickListener {
            showHistorico()
        }

        //apaga o campo de aviso quando dados sao inseridos
        etAltura.doOnTextChanged { _, _, _, _ -> tvResultado.text = "" }
        etPeso.doOnTextChanged { _, _, _, _ -> tvResultado.text = "" }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun guardarHistorico(peso: String, altura: String) {
        val date = LocalDate.now()
        val parsedDate = date.format(DateTimeFormatter.ISO_DATE).toString()
        val db = HelperDB(this)
        val newIMC = returnIMC(peso, altura)

        if (newIMC == "erro") return
        else {
            val newIMCData = DataIMC(0, parsedDate, peso, newIMC)
            db.adicionarIMC(newIMCData)
        }
    }

    private fun showHistorico() {
        val db = HelperDB(this)
        val listaDeHistoricoIMC = db.lerHistorico()
        val intent = Intent(this, HistoricoActivity::class.java).apply {
           putExtra(EXTRA_MESSAGE, listaDeHistoricoIMC)
        }
        startActivity(intent)
    }

    private fun returnIMC(peso: String, altura: String): String {
        return if (peso.isEmpty() || altura.isEmpty()) {
            tvResultado.text = "Favor inserir peso e altura"
            "erro"
        } else {
            val imc = peso.toDouble() / altura.toDouble() / altura.toDouble()
            String.format("%.1f", imc)
        }
    }

    private fun mostrarIMC(imc: String) {
        if (imc == "erro") {
            return
        } else {
            val doubleIMC = imc.toDouble()
            tvResultado.text = "Seu IMC é $doubleIMC"

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
            when (doubleIMC) {
                in 0.0..16.9 -> {
                    Faixa1.setTextColor(Color.parseColor("#FF5200")); Sign1.setTextColor(
                        Color.parseColor(
                            "#FF5200"
                        )
                    )
                }
                in 17.0..18.4 -> {
                    Faixa2.setTextColor(Color.parseColor("#FFA300")); Sign2.setTextColor(
                        Color.parseColor(
                            "#FFA300"
                        )
                    )
                }
                in 18.5..24.9 -> {
                    Faixa3.setTextColor(Color.parseColor("#45FF00")); Sign3.setTextColor(
                        Color.parseColor(
                            "#45FF00"
                        )
                    )
                }
                in 25.0..29.9 -> {
                    Faixa4.setTextColor(Color.parseColor("#FFA300")); Sign4.setTextColor(
                        Color.parseColor(
                            "#FFA300"
                        )
                    )
                }
                in 30.0..34.9 -> {
                    Faixa5.setTextColor(Color.parseColor("#FF000D")); Sign5.setTextColor(
                        Color.parseColor(
                            "#FF000D"
                        )
                    )
                }
                in 35.0..39.9 -> {
                    Faixa6.setTextColor(Color.parseColor("#FF000D")); Sign6.setTextColor(
                        Color.parseColor(
                            "#FF000D"
                        )
                    )
                }
                else -> {Faixa7.setTextColor(Color.parseColor("#FF000D")); Sign7.setTextColor(
                    Color.parseColor(
                        "#FF000D"
                    )
                )
                }
            }
        }
    }
}